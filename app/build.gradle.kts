plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kover)
}

android {
    namespace = "com.poatek.ci_testing_sample"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.poatek.ci_testing_sample"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {


    implementation(libs.imperiya) {
        artifact {
            classifier = "compose"
            type = "aar"
        }
    }
    implementation(platform(libs.androidx.compose.bom))

    implementation(project(":movies"))
    implementation(libs.google.material)
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.livedata)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.compose.tooling.preview)
    implementation(libs.androidx.compose.extended.icons)
    implementation(libs.androidx.compose.paging)
    implementation(libs.koin.compose)
    implementation(libs.compose.shimmer)

    implementation(libs.shimmer)
    implementation(libs.zenet.extensions.toolkit)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation(libs.androidx.paging)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.startup)
    implementation(libs.pagerindicator)
    implementation(libs.zenet.request)
    implementation(libs.android.lifecycle.livedata)
    implementation(libs.toolkit.delegate)

    implementation(libs.toolkit.statemachine)
    implementation(libs.coil.core)
    implementation(libs.coil.compose)
    implementation(libs.google.material)

    debugImplementation(libs.bigbrother.core)
    debugImplementation(libs.bigbrother.network)
    debugImplementation(libs.bigbrother.log)
}