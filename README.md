# Star Wars Planets

Want to add SWPlanets to your dev environment ? Sure !

Kotlin DSL:
```kotlin
repositories {
    maven(url = "https://maven.odysseyus.fr/releases/")
}

dependencies {
    "modImplementation"(group = "fr.tathan.swplanets", name = "swplanets-[MODLOADER]-[MINECRAFT_VERSION]", version = [MOD_VERSION])
}
```

Groovy DSL:
```groovy
repositories {
    maven {
      name "odysseyusMaven"
      url "https://maven.odysseyus.fr/releases"
    }
}

dependencies {
  implementation "fr.tathan.swplanets:swplanets-[MODLOADER]-[MINECRAFT_VERSION]:[MOD_VERSION]"
}
```
