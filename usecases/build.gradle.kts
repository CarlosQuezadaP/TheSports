import appdependencies.Builds
import appdependencies.Builds.BUILD_TOOLS
import appdependencies.Builds.COMPILE_VERSION
import appdependencies.Builds.MIN_VERSION
import appdependencies.Builds.TARGET_VERSION
import appdependencies.Versions

plugins {
    id(appdependencies.Plugins.id_android_library)
    id(appdependencies.Plugins.id_kotlin_android)
}

android {
    compileSdkVersion(COMPILE_VERSION)
    buildToolsVersion = BUILD_TOOLS

    defaultConfig {
        minSdkVersion(MIN_VERSION)
        targetSdkVersion(TARGET_VERSION)
        versionCode = Builds.App.VERSION_CODE
        versionName = Builds.App.VERSION_NAME
        testInstrumentationRunner = appdependencies.Libs.test_runner
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = Versions.jvmTarget
    }
}

dependencies {
    //Modules
    implementation(project(appdependencies.Modules.core))
    implementation(project(appdependencies.Modules.domain))

    //Libraries
    implementation(appdependencies.Libs.kotlin_coroutines)
    implementation(appdependencies.Libs.Koin.koin)

    //Test
    testImplementation(appdependencies.Libs.Testing.junit)
    testImplementation(appdependencies.Libs.Testing.coroutines_test)
    testImplementation(appdependencies.Libs.Testing.core_testing)
    testImplementation(appdependencies.Libs.Testing.mock_io)
    androidTestImplementation(appdependencies.Libs.Testing.mock_io_android)
}