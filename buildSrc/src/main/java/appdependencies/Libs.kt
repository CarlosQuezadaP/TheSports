package appdependencies


object Libs {
    const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
    const val androidx_core = "androidx.core:core-ktx:1.2.0"
    const val appcompat = "androidx.appcompat:appcompat:${Versions.appCompatX}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val constraintlayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    const val kotlin_coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val kotlin_coroutines_android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
}