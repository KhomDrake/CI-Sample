import kotlinx.kover.gradle.plugin.dsl.AggregationType
import kotlinx.kover.gradle.plugin.dsl.MetricType

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kover)
}

android {
    namespace = "com.poatek.movies"
    compileSdk = 34

    defaultConfig {
        minSdk = 26

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

koverReport {

    filters {
        excludes {
            annotatedBy("androidx.compose.ui.tooling.preview.Preview")
        }
    }
    verify {
        rule("Line Coverage") {
            isEnabled = true
            bound {
                minValue = 80 // Minimum coverage percentage
                metric = MetricType.LINE
                aggregation = AggregationType.COVERED_PERCENTAGE
            }
        }
        rule("Branch Coverage") {
            isEnabled = true
            bound {
                minValue = 90
                metric = MetricType.BRANCH
                aggregation = AggregationType.COVERED_PERCENTAGE
            }
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

    debugImplementation(libs.tests.compose.ui.manifest)

    testImplementation("org.robolectric:robolectric:4.11.1")
    testImplementation("com.willowtreeapps.assertk:assertk:0.28.1")
    testImplementation(libs.tests.compose.ui.test)
    testImplementation(libs.junit)
    testImplementation(libs.tests.androidx.core.testing)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.tests.compose.ui.test)
    androidTestImplementation(libs.tests.compose.ui.manifest)
    androidTestImplementation(libs.androidx.espresso.core)
}