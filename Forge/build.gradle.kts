architectury {
    forge()
}

loom {
    forge {
        mixinConfig("swplanets.mixins.json")
    }

    runs {
        create("data") {
            data()
            programArgs("--all", "--mod", "swplanets")
            programArgs("--output", project(":common").file("src/main/generated/resources").absolutePath)
            programArgs("--existing", project(":common").file("src/main/resources").absolutePath)
        }
    }
}

val common: Configuration by configurations.creating {
    configurations.compileClasspath.get().extendsFrom(this)
    configurations.runtimeClasspath.get().extendsFrom(this)
    configurations["developmentForge"].extendsFrom(this)
}

dependencies {
    common(project(":common", configuration = "namedElements")) {
        isTransitive = false
    }
    shadowCommon(project(path = ":common", configuration = "transformProductionForge")) {
        isTransitive = false
    }

    val minecraftVersion: String by project
    val forgeVersion: String by project
    val jeiVersion: String by project
    val adastraVersion: String by project


    forge(group = "net.minecraftforge", name = "forge", version = "$minecraftVersion-$forgeVersion")

    modLocalRuntime(group = "mezz.jei", name = "jei-$minecraftVersion-forge", version = jeiVersion) {
        isTransitive = false
    }
    "modImplementation"(group = "earth.terrarium.adastra", name = "ad_astra-forge-$minecraftVersion", version = adastraVersion)
    forgeRuntimeLibrary(include(group = "io.github.llamalad7", name = "mixinextras-forge", version = "0.4.1"))


//    modLocalRuntime(group = "maven.modrinth", name = "jade", version = "13.2.2")
//    modLocalRuntime(group = "maven.modrinth", name = "mekanism", version = "10.4.2.16")

    forgeRuntimeLibrary("com.teamresourceful:yabn:1.0.3")
    forgeRuntimeLibrary("com.teamresourceful:bytecodecs:1.0.2")
    forgeRuntimeLibrary(group = "javazoom", name = "jlayer", version = "1.0.1")
}

