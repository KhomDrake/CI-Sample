package com.poatek.movies.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.khomdrake.imperiya.ui.preview.BackgroundPreview
import br.com.khomdrake.imperiya.ui.theme.ImperiyaTheme
import br.com.khomdrake.imperiya.ui.theme.ImperiyaTypography
import com.poatek.movies.repository.Genre
import java.time.LocalDate

class MovieData(
    val id: String,
    val name: String,
    val localDate: LocalDate,
    val genre: Genre
)

enum class MovieViewTags {
    ROOT,
    NAME,
    DATE,
    GENRE
}

@Composable
fun Movie(
    movie: MovieData,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .background(MaterialTheme.colorScheme.secondaryContainer, RoundedCornerShape(16.dp))
            .padding(16.dp)
            .testTag(MovieViewTags.ROOT.name)
    ) {
        Text(
            text = movie.name,
            modifier = Modifier
                .fillMaxWidth()
                .testTag(MovieViewTags.NAME.name),
            style = ImperiyaTypography.TitleStyle,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = movie.localDate.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .testTag(MovieViewTags.DATE.name),
            style = ImperiyaTypography.ParagraphStyle,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = movie.genre.name.lowercase(),
            modifier = Modifier
                .fillMaxWidth()
                .testTag(MovieViewTags.GENRE.name),
            style = ImperiyaTypography.ParagraphBoldStyle,
            color = MaterialTheme.colorScheme.onSecondaryContainer
        )

    }
}

@Preview
@Composable
fun MoviePreview() {
    ImperiyaTheme {
        BackgroundPreview {
            Movie(
                movie = MovieData(
                    "abc",
                    "Avengers",
                    LocalDate.now().minusWeeks(3),
                    Genre.ACTION
                ),
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}