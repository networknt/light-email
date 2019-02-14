plugins {
    base
    kotlin("jvm") version "1.3.21" apply false
    // https://github.com/ben-manes/gradle-versions-plugin
    id("com.github.ben-manes.versions") version "0.20.0"
}


dependencies {
    // Make the root project archives configuration depend on every subproject
    subprojects.forEach {
        archives(it)
    }
}

allprojects {
    group = "com.networknt"
    version = "1.0.0"

    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions.jvmTarget = "1.8"
    }

    repositories {
        mavenLocal() // mavenLocal must be added first.
        jcenter()
    }
}
