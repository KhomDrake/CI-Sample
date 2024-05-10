package com.poatek.movies

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.khomdrake.imperiya.ui.preview.BackgroundPreview
import br.com.khomdrake.imperiya.ui.theme.ImperiyaTheme
import com.poatek.movies.repository.Genre
import com.poatek.movies.ui.Movie
import com.poatek.movies.ui.MovieData
import com.poatek.movies.ui.MovieViewTags
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.shadows.ShadowLog
import java.time.LocalDate

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class MovieTest {

    @get:Rule
    val composeRule = createComposeRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        ShadowLog.stream = System.out // Redirect Logcat to console
    }

    @Test
    fun movieTest() {
        val movie = MovieData(
            "abc",
            "Avengers",
            LocalDate.now().minusWeeks(3),
            Genre.ACTION
        )
        composeRule.setContent {
            ImperiyaTheme {
                BackgroundPreview {
                    Movie(movie = movie, modifier = Modifier.fillMaxWidth())
                }
            }
        }

        composeRule
            .apply {
                onNodeWithTag(MovieViewTags.ROOT.name)
                    .assertIsDisplayed()

                onNodeWithTag(MovieViewTags.GENRE.name)
                    .assertTextEquals("action")

                onNodeWithTag(MovieViewTags.NAME.name)
                    .assertTextEquals("Avengers")

                onNodeWithTag(MovieViewTags.DATE.name)
                    .assertTextEquals(LocalDate.now().minusWeeks(3).toString())
            }
    }
}