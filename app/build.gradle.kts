plugins {
    id("com.android.application")
    id("kotlin-android")
    kotlin("kapt")
}

android {
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    compileSdkVersion(Android.compileSdkVersion)
    buildToolsVersion(Android.buildToolsVersion)

    defaultConfig {
        applicationId(Android.applicationId)
        minSdkVersion(Android.minSdkVersion)
        targetSdkVersion(Android.targetSdkVersion)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField("String", ApiKeys.fieldApiTMDb, ApiKeys.apiTMDb)
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility("${JavaVersion.VERSION_1_8}")
        targetCompatibility("${JavaVersion.VERSION_1_8}")
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(Libs.kotlin_stdlib)
    implementation(Libs.core_ktx)
    implementation(Libs.appcompat)
    implementation(Libs.materialElements)
    implementation(Libs.constraint_layout)

    implementation(Libs.roomRuntime)
    kapt(Libs.roomPersistenceCompiler)
    annotationProcessor(Libs.roomCompiler)

    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.ext_junit)
    androidTestImplementation(TestLibs.espresso)
}