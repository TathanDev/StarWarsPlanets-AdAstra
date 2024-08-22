import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import dev.architectury.plugin.ArchitectPluginExtension
import groovy.json.StringEscapeUtils
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.fabricmc.loom.task.RemapJarTask

plugins {
    java
    id("maven-publish")
    id("dev.architectury.loom") version "1.7-SNAPSHOT" apply false
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("com.modrinth.minotaur") version "2.+"
    id("me.modmuss50.mod-publish-plugin") version "0.6.3"

}

architectury {
    val minecraftVersion: String by project
    minecraft = minecraftVersion
}


subprojects {
    apply(plugin = "maven-publish")
    apply(plugin = "dev.architectury.loom")
    apply(plugin = "architectury-plugin")
    apply(plugin = "com.github.johnrengelman.shadow")
    apply(plugin = "com.modrinth.minotaur")
    apply(plugin = "me.modmuss50.mod-publish-plugin")

    val minecraftVersion: String by project
    val version: String by project

    val modLoader = project.name
    val modId = rootProject.name
    val isCommon = modLoader == rootProject.projects.common.name

    base {
        archivesName.set("$modId-$modLoader-$minecraftVersion")
    }

    configure<LoomGradleExtensionAPI> {
        silentMojangMappingsLicense()

    }

    repositories {
        mavenCentral()
        maven(url = "https://maven.teamresourceful.com/repository/maven-public/")
        maven(url = "https://maven.neoforged.net/releases/")
        maven(url = "https://maven.firstdarkdev.xyz/snapshots")
        maven {
            url = uri("https://www.cursemaven.com")
            content {
                includeGroup("curse.maven")
            }
        }
        exclusiveContent {
            forRepository {
                maven {
                    name = "Modrinth"
                    url = uri("https://api.modrinth.com/maven")
                }
            }
            filter {
                includeGroup("maven.modrinth")
            }
        }
    }

    dependencies {
        val mixinExtrasVersion: String by project
        val resourcefulLibVersion: String by project
        val resourcefulConfigVersion: String by project
        val botariumVersion: String by project
        val jeiVersion: String by project
        val reiVersion: String by project
        val patchouliVersion: String by project
        val shimmerVersion: String by project
        val adastraVersion: String by project
        val cadmusVersion: String by project

        val version: String by project

        "minecraft"("::$minecraftVersion")

        @Suppress("UnstableApiUsage")
        "mappings"(project.the<LoomGradleExtensionAPI>().layered {
            val parchmentVersion: String by project

            officialMojangMappings()

            parchment(create(group = "org.parchmentmc.data", name = "parchment-1.20.3", version = parchmentVersion))
        })

        "modApi"(group = "com.teamresourceful.resourcefullib", name = "resourcefullib-$modLoader-$minecraftVersion", version = resourcefulLibVersion)
        "modApi"(group = "com.teamresourceful.resourcefulconfig", name = "resourcefulconfig-$modLoader-$minecraftVersion", version = resourcefulConfigVersion)
        "modApi"(group = "earth.terrarium.botarium", name = "botarium-$modLoader-$minecraftVersion", version = botariumVersion)
        "modImplementation"(group = "earth.terrarium.adastra", name = "ad_astra-$modLoader-$minecraftVersion", version = adastraVersion)

        "modCompileOnly"(group = "earth.terrarium.cadmus", name = "cadmus-$modLoader-$minecraftVersion", version = cadmusVersion) {
            isTransitive = false
        }


        if (isCommon) {
            implementation(group = "javazoom", name = "jlayer", version = "1.0.1")
            "modApi"(group = "mezz.jei", name = "jei-$minecraftVersion-common-api", version = jeiVersion)
            "modCompileOnly"(group = "me.shedaniel", name = "RoughlyEnoughItems-api", version = reiVersion)
            "modCompileOnly"(group = "me.shedaniel", name = "RoughlyEnoughItems-default-plugin", version = reiVersion)
            implementation("annotationProcessor"(group = "io.github.llamalad7", name = "mixinextras-common", version = mixinExtrasVersion))

        } else {
            "include"(implementation(group = "javazoom", name = "jlayer", version = "1.0.1"))
            "modCompileOnly"(group = "me.shedaniel", name = "RoughlyEnoughItems-api-$modLoader", version = reiVersion)
            "modCompileOnly"(group = "me.shedaniel", name = "RoughlyEnoughItems-default-plugin-$modLoader", version = reiVersion)
            "modLocalRuntime"(group = "vazkii.patchouli", name = "Patchouli", version = "$minecraftVersion-$patchouliVersion-${modLoader.uppercase()}")
//            "modLocalRuntime"(group = "com.lowdragmc.shimmer", name = "Shimmer-$modLoader", version = "$minecraftVersion-$shimmerVersion") { isTransitive = false }
        }
    }

    java {
        withSourcesJar()
    }

    tasks.jar {
        archiveClassifier.set("dev")
    }

    tasks.named<RemapJarTask>("remapJar") {
        archiveClassifier.set(null as String?)
    }

    tasks.processResources {
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
        filesMatching(listOf("META-INF/mods.toml", "fabric.mod.json")) {
            expand("version" to project.version)
        }
    }

    if (!isCommon) {
        configure<ArchitectPluginExtension> {
            platformSetupLoomIde()
        }

        val shadowCommon by configurations.creating {
            isCanBeConsumed = false
            isCanBeResolved = true
        }

        tasks {
            "shadowJar"(ShadowJar::class) {
                archiveClassifier.set("dev-shadow")
                configurations = listOf(shadowCommon)

                exclude(".cache/**") // Remove datagen cache from jar.
                exclude("**/swplanets/datagen/**") // Remove data gen code from jar.
            }

            "remapJar"(RemapJarTask::class) {
                dependsOn("shadowJar")
                inputFile.set(named<ShadowJar>("shadowJar").flatMap { it.archiveFile })
            }
        }
    } else {
        sourceSets.main.get().resources.srcDir("src/main/generated/resources")
    }

    if (!isCommon) {
        publishing {
            publications {
                create<MavenPublication>("maven") {
                    artifactId = "$modId-$modLoader-$minecraftVersion"
                    from(components["java"])

                    pom {
                        name.set("Star Wars Planets $modLoader")
                        url.set("https://github.com/TathanDev/StarWarsPlanets-AdAstra/")

                        scm {
                            connection.set("git:https://github.com/TathanDev/StarWarsPlanets-AdAstra.git")
                            developerConnection.set("git:https://github.com/TathanDev/StarWarsPlanets-AdAstra.git")
                            url.set("https://github.com/TathanDev/StarWarsPlanets-AdAstra")
                        }
                    }
                }
            }
            repositories {
                maven {
                    setUrl("http://maven.odysseyus.fr/releases/")
                    setAllowInsecureProtocol(true)

                    credentials {
                        username = System.getenv("MAVEN_USER")
                        password = System.getenv("MAVEN_PASS")
                    }
                }
            }
        }

        publishMods {

            file = file("../${modLoader.capitalize()}/build/libs/swplanets-${modLoader}-${minecraftVersion}-${version}.jar")
            changelog = file("../changelog.md").readText(Charsets.UTF_8)
            type = STABLE
            modLoaders.add(modLoader)
            displayName = "[${modLoader.capitalize()}] Star Wars Planets ${version}"

            curseforge {
                projectId = "725821"
                projectSlug = "star-wars-planets-ad-astra" // Required for discord webhook
                accessToken = providers.environmentVariable("CURSEFORGE_API_KEY")
                minecraftVersions.add("${minecraftVersion}")
                requires("ad-astra")
            }

            modrinth {
                projectId = "sgQirvDi"
                accessToken = providers.environmentVariable("MODRINTH_TOKEN")
                minecraftVersions.add("${minecraftVersion}")
                requires("ad-astra")

            }
        }

    }


}
