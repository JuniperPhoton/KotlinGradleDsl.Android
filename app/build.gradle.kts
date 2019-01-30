import com.android.build.gradle.api.ApplicationVariant
import com.android.build.gradle.api.BaseVariantOutput
import com.android.build.gradle.internal.api.BaseVariantOutputImpl

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

apply(from = "../version.gradle.kts")
apply(from = "../source.gradle.kts")

fun Project.hasLocalProperty(key: String): Boolean {
    return extra.properties.containsKey(key)
}

val compileSDKVersion: Int by extra
val minSDKVersion: Int by extra
val targetSDKVersion: Int by extra
val kotlinVersion: String by extra
val constraintLayoutVersion: String by extra
val androidKtxVersion: String by extra
val androidAppCompatVersion: String by extra

// Just demonstrate another way to get extra value
val appVersionCode = extra["appVersionCode"] as Int
val appVersionName = extra["appVersionName"] as String

android {
    signingConfigs {
        create("release") {
            val key: String? by project
            val password: String? by project

            if (key != null && password != null) {
                keyAlias = key
                keyPassword = password
                storeFile = File("keystore/ms.jks")
                storePassword = password
            }
        }
    }

    compileSdkVersion(compileSDKVersion)
    defaultConfig {
        applicationId = "com.juniperphoton.android.ktdsltest"
        minSdkVersion(minSDKVersion)
        targetSdkVersion(targetSDKVersion)
        versionCode = appVersionCode
        versionName = appVersionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

        // ManifestPlaceholders
        mapOf("MAIN_LABEL" to "main")
            .forEach {
                manifestPlaceholders[it.key] = it.value
            }
    }

    @Suppress("ObjectLiteralToLambda")
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            // Change the apk name to "name_changed.apk"
            // NOTE: do not use forEach method since the variants is not yet generated.
            // the action in "all" method will apply to all the variants in build phase.
            android.applicationVariants.all(object : Action<ApplicationVariant> {
                override fun execute(t: ApplicationVariant) {
                    t.outputs.all(object : Action<BaseVariantOutput> {
                        override fun execute(t: BaseVariantOutput) {
                            (t as? BaseVariantOutputImpl)?.outputFileName = "name_changed.apk"
                        }
                    })
                }
            })
        }
    }

    flavorDimensions("default")

    productFlavors {
        create("alpha") {
            setDimension("default")
            applicationIdSuffix = "alpha"
        }
        create("beta") {
            setDimension("default")
            applicationIdSuffix = "beta"
        }
    }

    sourceSets {
        get("main").java.srcDirs("src/main/kotlin")
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    val hasLocal = rootProject.hasLocalProperty("useLocalRepo")
    println("has local: $hasLocal")

    val hasName = rootProject.hasLocalProperty("userName")
    println("has userName: $hasName")

    val hasPwd = rootProject.hasLocalProperty("userPassword")
    println("has userPassword: $hasPwd")
}

dependencies {
    // Equals add("implementation", fileTree(mapOf("dir" to "libs", "include" to "*.jar")))
    implementation(fileTree(mapOf("dir" to "libs", "include" to arrayOf("*.jar"))))

    implementation(project(":lib"))

    add("implementation", "org.jetbrains.kotlin:kotlin-stdlib-jdk7:$kotlinVersion")
    add("implementation", "androidx.appcompat:appcompat:$androidAppCompatVersion")
    add("implementation", "androidx.core:core-ktx:$androidKtxVersion")
    add("implementation", "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion")

    androidTestImplementation("androidx.test.espresso:espresso-core:3.1.1") {
        exclude(group = "com.android.support", module = "support-annotations")
    }
}