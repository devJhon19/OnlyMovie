private const val kotlinVersion = "1.3.72"
private const val androidGradleVersion = "4.1.1"
private const val androidMavenGradlePluginVersion = "2.1"

// Compile dependencies
private const val daggerVersion = "2.14.1"
private const val retrofitVersion = "2.3.0"
private const val okhttpVersion = "3.9.1"
private const val glideVersion = "4.11.0"
private const val lottieVersion = "3.4.0"
private const val constraintLayoutVersion = "2.0.4"
private const val androidxLifecycle = "2.0.0"
private const val roomVersion = "2.2.6"
private const val materialElementsVersion = "1.3.0"
private const val appcompatVersion = "1.2.0"
private const val corektxVersion = "1.3.2"

object Android {
    val buildToolsVersion = "30.0.3"
    val minSdkVersion = 23
    val targetSdkVersion = 30
    val compileSdkVersion = 30
    val applicationId = "com.dev.jhon.onlymovie"
    val versionCode = 1
    val versionName = "0.0.1"

}

object ApiKeys {
    val fieldApiTMDb = "ApiTMDb"
    val apiTMDb = "b9b2327f33555fcdbfcf4a5c4308d46c"
}

object BuildPlugins {
    val androidGradle = "com.android.tools.build:gradle:$androidGradleVersion"
    val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    val androidMavenGradlePlugin = "com.github.dcendents:android-maven-gradle-plugin:$androidMavenGradlePluginVersion"
}

object Libs {
    val appcompat = "androidx.appcompat:appcompat:$appcompatVersion"
    val core_ktx = "androidx.core:core-ktx:$corektxVersion"
    val kotlin_stdlib = "org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion"
    val glide = "com.github.bumptech.glide:glide:$glideVersion"
    val glide_annotation_processor = "com.github.bumptech.glide:compiler:$glideVersion"
    val lottie = "com.airbnb.android:lottie:$lottieVersion"
    val okhttp = "com.squareup.okhttp3:okhttp:$okhttpVersion"
    val okhttp_interceptor = "com.squareup.okhttp3:logging-interceptor:$okhttpVersion"
    val retrofit = "com.squareup.retrofit2:retrofit:$retrofitVersion"
    val retrofit_gson = "com.squareup.retrofit2:converter-gson:$retrofitVersion"
    val dagger_compiler = "com.google.dagger:dagger-compiler:$daggerVersion"
    val dagger = "com.google.dagger:dagger:$daggerVersion"
    val materialElements = "com.google.android.material:material:$materialElementsVersion"
    val constraint_layout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"
    val androidx_lifecycle = "androidx.lifecycle:lifecycle-extensions:$androidxLifecycle"
    val roomRuntime = "android.arch.persistence.room:runtime:$roomVersion"
    val roomPersistenceCompiler = "android.arch.persistence.room:compiler:$roomVersion"
    val roomCompiler = "androidx.room:room-compiler:$roomVersion"
}

object TestLibs {
    val junit = "junit:junit:4.12"
    val ext_junit = "androidx.test.ext:junit:1.1.2"
    val espresso = "androidx.test.espresso:espresso-core:3.3.0"
}