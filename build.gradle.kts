import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import dev.architectury.plugin.ArchitectPluginExtension
import groovy.json.StringEscapeUtils
import net.fabricmc.loom.api.LoomGradleExtensionAPI
import net.fabricmc.loom.task.RemapJarTask

plugins {
    java
    id("maven-publish")
    id("dev.architectury.loom") version "1.4-SNAPSHOT" apply false
    id("architectury-plugin") version "3.4-SNAPSHOT"
    id("com.github.johnrengelman.shadow") version "7.1.2" apply false
    id("com.modrinth.minotaur") version "2.+"

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

    val minecraftVersion: String by project
    val  modLoader = project.name
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
        "modApi"(group = "earth.terrarium.adastra", name = "adastra-$modLoader-$minecraftVersion", version = adastraVersion)

        "modCompileOnly"(group = "earth.terrarium.cadmus", name = "cadmus-$modLoader-$minecraftVersion", version = "1.2.1") {
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
//            "modLocalRuntime"(group = "vazkii.patchouli", name = "Patchouli", version = "$minecraftVersion-$patchouliVersion-${modLoader.uppercase()}")
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

    publishing {
        publications {
            create<MavenPublication>("maven") {
                artifactId = "$modId-$modLoader-$minecraftVersion"
                from(components["java"])

                pom {
                    name.set("Ad Astra $modLoader")
                    url.set("https://github.com/terrarium-earth/$modId")

                    scm {
                        connection.set("git:https://github.com/terrarium-earth/$modId.git")
                        developerConnection.set("git:https://github.com/terrarium-earth/$modId.git")
                        url.set("https://github.com/terrarium-earth/$modId")
                    }

                    licenses {
                        license {
                            name.set("Terrarium Licence (https://gist.github.com/CodexAdrian/4bb2a1868bb2d2a91ca74ea40424e69d)")
                        }
                    }
                }
            }
        }
        repositories {
            maven {
                setUrl("https://maven.teamresourceful.com/repository/terrarium/")
                credentials {
                    username = System.getenv("MAVEN_USER")
                    password = System.getenv("MAVEN_PASS")
                }
            }
        }
    }

    modrinth {
        token.set(System.getenv("MODRINTH_TOKEN")) // Remember to have the MODRINTH_TOKEN environment variable set or else this will fail - just make sure it stays private!
        projectId = "star-wars-planets-ad-astra" // This can be the project ID or the slug. Either will work!
        versionNumber.set("$version") // You don't need to set this manually. Will fail if Modrinth has this version already
        versionType.set("release") // This is the default -- can also be `beta` or `alpha`
        versionName = "[${modLoader.uppercase()}] SWPlanets ${version}"// This can be the project ID or the slug. Either will work!
        changelog = file("../changelog.md").readText(Charsets.UTF_8) // This is the changelog for the version
        uploadFile.set(tasks.findByPath("remapJar")) // With Loom, this MUST be set to `remapJar` instead of `jar`!
        gameVersions.addAll("1.20.4") // Must be an array, even with only one version
        loaders.add("$modLoader") // Must also be an array - no need to specify this if you're using Loom or ForgeGradle
        dependencies { // A special DSL for creating dependencies
            required.project("ad-astra", "resourceful-lib", "resourceful-config", "botarium")
        }
    }

}
