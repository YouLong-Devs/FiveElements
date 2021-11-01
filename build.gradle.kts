plugins {
    java
    id("io.izzel.taboolib") version "1.31"
    id("org.jetbrains.kotlin.jvm") version "1.5.31"
}

taboolib {
    description {
        contributors {
            name("kzheart")
        }
        dependencies {
            name("MythicMobs")
            name("SkillAPI")
            name("AttributePlus")
            name("PlaceholderAPI").optional(true)
        }
    }
    install("common")
    install("common-5")
    install("module-configuration")
    install("module-database")
    install("module-lang")
    install("module-chat")
    install("platform-bukkit")
    install("expansion-command-helper")
    version = "6.0.3-21"
}

repositories {
    mavenLocal()
    mavenCentral()
    maven("https://repo.extendedclip.com/content/repositories/placeholderapi/")
}

dependencies {
    compileOnly("ink.ptms:nms-all:1.0.0")
    compileOnly("ink.ptms.core:v11200:11200:all")
//    compileOnly("ink.ptms.core:v11701:11701:mapped")
//    compileOnly("ink.ptms.core:v11701:11701:universal")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
    compileOnly("io.izzel.taboolib:TabooLib:5.7.2:all")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}
