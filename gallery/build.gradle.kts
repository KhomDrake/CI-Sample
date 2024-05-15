import com.android.build.gradle.internal.tasks.DeviceProviderInstrumentTestTask
import com.android.build.gradle.internal.tasks.JacocoTask
//import kotlinx.kover.features.jvm.ClassFilters
//import kotlinx.kover.features.jvm.KoverFeatures
//import kotlinx.kover.features.jvm.KoverLegacyFeatures
import java.util.Base64

plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsKotlinAndroid)
    alias(libs.plugins.kover)
    id("jacoco")
}

apply(from = "$rootDir/gradle/coverage-combined.gradle")

//buildscript {
//    dependencies {
//        // Kover: adding dependency to perform instrumentation and generate reports
//        classpath(libs.kover.feautures)
//    }
//}

kover {
    disable()
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



// =====
// Kover: Kover-only settings
// =====
//dependencies {
//    // dependency to Kover runtime (required for applications, instrumented offline)
//    implementation(libs.kover.offline)
//}

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
//    implementation(libs.kover.offline)

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

//// After JaCoCo instrumentation is performed - replace these classes by classes instrumented with Kover
//tasks.withType<JacocoTask>().configureEach {
//    doLast {
//        val instrumentedClassesDir = this@configureEach.outputForDirs.get().asFile
//        val originalClassesDir = this@configureEach.classesDir
//
//        // perform instrumentation
//        val instrumenter = KoverFeatures.createOfflineInstrumenter()
//
//        originalClassesDir.forEach { originalRootDir ->
//            originalRootDir.walk().forEach { originFile ->
//                if (originFile.isFile && originFile.name.endsWith(".class")) {
//                    // instrument classfile by Kover and rewrite file content
//                    val koverInstrumentedBytes = instrumenter.instrument(
//                        originFile.readBytes().inputStream(),
//                        originFile.name
//                    )
//
//                    val relativePath = originFile.toRelativeString(originalRootDir)
//                    val fileToRewrite = instrumentedClassesDir.resolve(relativePath)
//                    fileToRewrite.writeBytes(koverInstrumentedBytes)
//                }
//            }
//        }
//
//    }
//}
//
//tasks.register("koverHtmlReport") {
//    group = LifecycleBasePlugin.VERIFICATION_GROUP
//
//    dependsOn("connectedDebugAndroidTest")
//
//    doLast {
//        val koverDir = layout.buildDirectory.dir("kover").get()
//        koverDir.asFile.mkdirs()
//
//        // get binary report file
//        val testTask =
//            tasks.withType<DeviceProviderInstrumentTestTask>().named("connectedDebugAndroidTest")
//                .get()
//        val testResultDir: File = testTask.resultsDir.get().asFile
//        println(testTask.resultsDir.asFileTree.toString())
//        println(testResultDir.findLogFile())
//        val reportBytes = testResultDir.findLogFile()?.extractKoverBinaryReport()
//        assert(reportBytes != null) { "Kover binary report wasn't found in logs" }
//        val reportFile = koverDir.file("report.ic").asFile
//        println(reportBytes)
//        reportFile.writeBytes(reportBytes!!)
//
//        // collect sources dirs
//        val sourcesDirs =
//            android.libraryVariants.first { it.name == "debug" }.sourceSets.flatMap {
//                it.javaDirectories + it.kotlinDirectories
//            }
//
//        // collect classfiles dirs
//        val jacoco = tasks.withType<JacocoTask>().named("jacocoDebug").get()
//        val classesDirs = jacoco.classesDir.toList()
//
//        // generate report
//        val htmlDir = koverDir.dir("html").asFile
//        KoverLegacyFeatures.generateHtmlReport(
//            htmlDir,
//            null,
//            listOf(reportFile),
//            classesDirs,
//            sourcesDirs,
//            "Example report",
//            ClassFilters(emptySet(), emptySet(), emptySet())
//        )
//
//        logger.quiet("Kover HTML report file://${htmlDir.absolutePath}/index.html")
//    }
//}
//
//
//fun File.findLogFile(): File? {
//    walk().forEach { file ->
//        if (file.isFile && file.name.startsWith("logcat-")) {
//            return file
//        }
//    }
//    return null
//}
//
//fun File.extractKoverBinaryReport(): ByteArray? {
//    val bytesString = readLines().map { line ->
//        line.substringAfter("KOVER DUMP=", "")
//    }.firstOrNull { it.isNotEmpty() } ?: return null
//
//    return Base64.getDecoder().decode(bytesString)
//}
