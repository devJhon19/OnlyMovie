plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-android-extensions")
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
        buildConfigField("String", ApiKeys.fieldUrlImageTMDb, ApiKeys.UrlImageTMDb)
        buildConfigField("String", ApiKeys.fieldUrlApiTMDb, ApiKeys.UrlApiTMDb)
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
    //RETROFIT
    implementation(Libs.retrofit)
    implementation(Libs.retrofit_gson)
    implementation(Libs.retrofit_rxjava2_adapter)
    //TOAST
    implementation(Libs.material_toast)
    //ROOM
    implementation(Libs.roomRuntime)
    kapt(Libs.roomPersistenceCompiler)
    annotationProcessor(Libs.roomCompiler)
    //GLIDE
    implementation(Libs.glide)
    annotationProcessor(Libs.glide_annotation_processor)
    implementation(Libs.gson)
    //TESTS
    testImplementation(TestLibs.junit)
    androidTestImplementation(TestLibs.ext_junit)
    androidTestImplementation(TestLibs.espresso)
    //MVVM
    implementation(Libs.lifecycle_extensions)
    implementation(Libs.lifecycle_viewmodel_ktx)
    //PAGING
    implementation(Libs.paging_runtime)
    testImplementation(Libs.paging_common) // For Kotlin use paging-common-ktx
    implementation(Libs.paging_rxjava2) // For Kotlin use paging-rxjava2-ktx
}