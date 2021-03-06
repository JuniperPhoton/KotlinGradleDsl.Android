// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    apply(from = "version.gradle.kts")

    repositories {
        google()
        jcenter()
    }
    dependencies {
        val kotlinVersion: String by project.extra

        classpath("com.android.tools.build:gradle:3.3.0")
        classpath(kotlin("gradle-plugin", version = kotlinVersion))

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}