package com.poatek.ci_testing_sample

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
//import kotlinx.kover.offline.runtime.api.KoverRuntime

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import java.util.Base64

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.poatek.ci_testing_sample", appContext.packageName)
    }
}