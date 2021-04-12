import appdependencies.Builds
import appdependencies.Builds.APP_ID
import appdependencies.Builds.BUILD_TOOLS
import appdependencies.Builds.COMPILE_VERSION
import appdependencies.Builds.MIN_VERSION
import appdependencies.Builds.TARGET_VERSION
import appdependencies.Libs
import appdependencies.Versions

plugins {
    id(appdependencies.Plugins.id_android_app)
    id(appdependencies.Plugins.id_kotlin_android)
    id(appdependencies.Plugins.id_navigation_safeargs)
    kotlin(appdependencies.Plugins.kotlin_android)
    kotlin(appdependencies.Plugins.kotlin_android_extensions)
    kotlin(appdependencies.Plugins.kotlin_kapt)
}

android {
    compileSdkVersion(COMPILE_VERSION)
    buildToolsVersion = BUILD_TOOLS
    defaultConfig {
        applicationId = APP_ID
        minSdkVersion(MIN_VERSION)
        targetSdkVersion(TARGET_VERSION)
        versionCode = Builds.App.VERSION_CODE
        versionName = Builds.App.VERSION_NAME
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isDebuggable = true
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:${rootProject.extra["kotlin_version"]}")
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.appcompat:appcompat:1.2.0")
    implementation("androidx.constraintlayout:constraintlayout:2.0.4")

    //modules
    api(project(appdependencies.Modules.core))
    api(project(appdependencies.Modules.usecases))
    api(project(appdependencies.Modules.domain))
    api(project(appdependencies.Modules.data))

    //Libraries
    implementation(Libs.Glide.glide)
    implementation(Libs.Koin.koin)
    implementation(Libs.Koin.koinViewModel)
    implementation(Libs.Koin.koinFragment)
    implementation(Libs.kotlin_coroutines)
    implementation(Libs.kotlin_coroutines_android)
    implementation(Libs.Lifecycle.viewmodelKtx)
    implementation(Libs.constraintlayout)
    implementation(Libs.androidx_core)
    implementation(Libs.material)
    implementation(Libs.appcompat)
    implementation(Libs.Core.navigationFragmentKtx)
    implementation(Libs.Core.navigationUiKtx)
    implementation(Libs.circleImage)

    kapt(Libs.Glide.glide_compiler)

    //Test
    testImplementation("junit:junit:4.+")
    androidTestImplementation("androidx.test.ext:junit:1.1.2")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.3.0")
}