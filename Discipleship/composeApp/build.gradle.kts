import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.compose.compiler)
    alias(libs.plugins.googleServices)
    kotlin("plugin.serialization")
    id("com.google.devtools.ksp") version "2.0.0-1.0.22"
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
            implementation(libs.calendar)
            implementation("androidx.lifecycle:lifecycle-viewmodel:2.8.2")

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

            // Firebase auth SDK
//            implementation("com.google.firebase:firebase-auth:21.0.0")
            implementation("dev.gitlive:firebase-auth:1.12.0")

            // Arrow for data and error handling
            implementation(libs.arrow.core)
            implementation(libs.arrow.fx.coroutines)

            // Logging library
            implementation(libs.kermit)

            // for API requests (GET, POST, etc)
            implementation(libs.ktor.client.core)

            // for serialization
            implementation(libs.kotlinx.serialization.json)
            // Ktor
            implementation(libs.ktor.client.cio) // Engine
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)
            // Ktorfit stuff for better handling JSON
            implementation(libs.ktorfit.lib)
            implementation(libs.ktorfit.converters.response)
            implementation(libs.ktorfit.converters.call)
            implementation(libs.ktorfit.converters.flow)

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
}

