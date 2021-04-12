package appdependencies


object Libs {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val kotlin_reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    const val circleImage = "de.hdodenhof:circleimageview:${Versions.circleImage}"
    const val androidx_core = "androidx.core:core-ktx:1.2.0"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompatX}"
    const val android_legacy = "androidx.legacy:legacy-support-v4:${Versions.android_legacy}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val kotlin_coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val kotlin_coroutines_android =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"


    object Glide {
        const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"
        const val glide_compiler = "com.github.bumptech.glide:compiler:${Versions.glide}"

    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
        const val kaptcompiler = "androidx.room:room-compiler:${Versions.room}"
    }

    object Koin {
        const val koin = "org.koin:koin-android:${Versions.koin}"
        const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"
        const val koinFragment = "org.koin:koin-androidx-fragment:${Versions.koin}"
    }

    object Retrofit {
        const val core = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
        const val gson = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
        const val adapter = "com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    }

    object Okhttp {
        const val okhttp = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
        const val logging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    }


    object Lifecycle {
        const val viewmodelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    }


    object Core {
        const val navigationFragmentKtx =
            "androidx.navigation:navigation-fragment-ktx:${Versions.Navigation.fragment}"
        const val navigationUiKtx =
            "androidx.navigation:navigation-ui-ktx:${Versions.Navigation.ui}"
    }



    object Testing {
        const val junit = "junit:junit:${Versions.Test.junit}"
        const val test_junit = "androidx.test.ext:junit:${Versions.Test.test_junit}"
        const val espresso_contrib = "androidx.test.espresso:espresso-contrib:${Versions.Test.espresso}"
        const val espresso_core= "androidx.test.espresso:espresso-core:${Versions.Test.espresso}"
        const val test_core = "androidx.test:core-ktx:${Versions.Test.androidx_tesst}"
        const val test_rules = "androidx.test:rules:${Versions.Test.androidx_tesst}"
        const val coroutines_test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.Test.coroutines_test}"
        const val core_testing = "androidx.arch.core:core-testing:${Versions.Test.core_testing}"
        const val mock_io = "io.mockk:mockk:${Versions.Test.mock_io}"
        const val mock_io_android = "io.mockk:mockk-android:${Versions.Test.mock_io_android}"
    }
}