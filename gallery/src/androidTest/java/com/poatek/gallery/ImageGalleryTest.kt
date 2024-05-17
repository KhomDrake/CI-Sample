package com.poatek.gallery

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.khomdrake.imperiya.ui.theme.ImperiyaTheme
import com.poatek.gallery.repository.GalleryData
import com.poatek.gallery.repository.Image
import com.poatek.gallery.ui.ImageGallery
import com.poatek.gallery.ui.ImageGalleryTag
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDate

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ImageGalleryTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Test
    fun withData_shouldDisplayRightInformation() {
        val today = LocalDate.now()
        val image = Image(
            GalleryData.imagesUrl.first(),
            today
        )

        composeRule.setContent {
            ImperiyaTheme {
                ImageGallery(
                    image = image
                )
            }
        }

        composeRule.apply {
            onNodeWithTag(ImageGalleryTag.ROOT.name)
                .assertIsDisplayed()

            onNodeWithTag(ImageGalleryTag.NAME.name)
                .apply {
                    assertIsDisplayed()
                    assertTextEquals(today.toString())
                }
        }
    }
}