import org.jetbrains.kotlin.gradle.plugin.extraProperties

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

apply(from = "$rootDir/gradle/report-and-verification-improved.gradle")

ext {
    set("coverageThreshold", .36)
}

android {
    namespace = "com.poatek.gallery"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        debug {
            enableUnitTestCoverage = true
            enableAndroidTestCoverage = true
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

    testOptions {
        animationsDisabled = true

        unitTests {
            isIncludeAndroidResources = true
        }
    }

    testCoverage {
        jacocoVersion = libs.versions.orgjacoco.core.version.get()
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
    implementation(libs.androidx.compose.runtime)
    implementation(libs.androidx.compose.activity)
    implementation(libs.androidx.compose.livedata)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.compose.constraintlayout)
    implementation(libs.androidx.compose.tooling.preview)
    implementation(libs.androidx.compose.extended.icons)
    implementation(libs.androidx.compose.paging)
    implementation(libs.zenet.request)
    implementation(libs.coil.core)
    implementation(libs.coil.compose)
    implementation(libs.toolkit.livedata)
    implementation(libs.shimmer)
    implementation(libs.google.material)
    implementation(libs.koin.compose)
    implementation(libs.compose.shimmer)
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.google.material)
    implementation(libs.androidx.compose.tooling.debug)
    implementation(libs.androidx.datastore)

    debugImplementation(libs.tests.compose.ui.manifest)

    testImplementation("com.willowtreeapps.assertk:assertk:0.28.1")
    testImplementation(libs.tests.compose.ui.test)
    testImplementation(libs.junit)
    testImplementation(libs.tests.androidx.core.testing)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.tests.compose.ui.test)
    androidTestImplementation(libs.tests.compose.ui.manifest)
    androidTestImplementation(libs.androidx.espresso.core)
}
