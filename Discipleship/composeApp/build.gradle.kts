import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.googleServices)
//    alias(libs.plugins.ksp)
//    alias(libs.plugins.room)
    kotlin("plugin.serialization")
    id("de.jensklingenberg.ktorfit") version "2.0.0"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    sourceSets {
        androidMain.dependencies {
            implementation(compose.preview)
            implementation(libs.androidx.activity.compose)
            implementation(libs.ktor.client.okhttp)
            implementation(libs.kotlinx.coroutines.android)
        }
        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
        commonMain.dependencies {
            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)
            implementation(compose.components.resources)

            // Calendar lib
            implementation(libs.calendar)

            // ViewModel lib
            implementation(libs.androidx.lifecycle.viewmodel)

            // Navigator
            implementation(libs.voyager.navigator)
            // Screen Model
            implementation(libs.voyager.screenmodel)
            // BottomSheetNavigator
            implementation(libs.voyager.bottom.sheet.navigator)
            // TabNavigator
            implementation(libs.voyager.tab.navigator)
            // Transitions
            implementation(libs.voyager.transitions)

            // Firebase auth library
            implementation(libs.gitlive.firebase.auth)

            // Coroutines
            implementation(libs.kotlinx.coroutines.core)

            // Arrow for data and error handling
            implementation(libs.arrow.core)
            implementation(libs.arrow.fx.coroutines)

            // Logging library
            implementation(libs.kermit)

            // For API/DB requests (GET, POST, etc)
            // JSON serialization
            implementation(libs.kotlinx.serialization.json)

            // Ktor
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            // Ktorfit stuff for better handling requests (not currently used)
            implementation(libs.ktorfit.lib)
            implementation(libs.ktorfit.converters.response)
            implementation(libs.ktorfit.converters.call)
            implementation(libs.ktorfit.converters.flow)

            // Room database
//            implementation(libs.androidx.room.gradle.plugin)
//
//            implementation(libs.room.compiler)
////            implementation(libs.androidx.sqlite)
//            implementation(libs.sqlite.bundled)
////            implementation(libs.room.runtime)

        }
    }
}

android {
    namespace = "kmp.project.discipleship"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "kmp.project.discipleship"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    buildFeatures {
        compose = true
    }
    dependencies {
        debugImplementation(compose.uiTooling)
    }
}

//room {
//    schemaDirectory("$projectDir/schemas")
//}

dependencies {
    implementation(libs.androidx.material3.android)
    implementation(libs.androidx.core)
    implementation(libs.androidx.foundation.android)
    implementation(libs.places)
    implementation(libs.androidx.media3.common)
    implementation(libs.androidx.compose.material)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.material.core)
    implementation(libs.firebase.common.ktx)
//    ksp(libs.room.compiler)
}