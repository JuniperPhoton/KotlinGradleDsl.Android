plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

apply(from = "../version.gradle.kts")

val compileSDKVersion: Int by extra
val minSDKVersion: Int by extra
val targetSDKVersion: Int by extra
val kotlinVersion: String by extra
val appVersionCode: Int by extra
val appVersionName: String by extra
val androidAppCompatVersion: String by extra

android {
    compileSdkVersion(compileSDKVersion)

    defaultConfig {
        minSdkVersion(minSDKVersion)
        targetSdkVersion(targetSDKVersion)
        versionCode = appVersionCode
        versionName = appVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    add("implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    add("implementation", "androidx.appcompat:appcompat:$androidAppCompatVersion")
}
