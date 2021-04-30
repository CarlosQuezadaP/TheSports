buildscript {
    val kotlin_version by extra("1.4.32")
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath(appdependencies.ClassPath.gradle)
        classpath(appdependencies.ClassPath.kotlingradle)
        classpath(appdependencies.ClassPath.safeargs)
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
