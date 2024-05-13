package com.poatek.gallery.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.khomdrake.imperiya.ui.preview.BackgroundPreview
import br.com.khomdrake.imperiya.ui.theme.ImperiyaTheme
import br.com.khomdrake.imperiya.ui.theme.ImperiyaTypography
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.poatek.gallery.repository.Image
import java.time.LocalDate

enum class ImageGalleryTag {
    ROOT,
    IMAGE,
    NAME
}

@Composable
fun ImageGallery(
    image: Image,
    modifier: Modifier = Modifier
) {
    Column(
        modifier
            .background(
                MaterialTheme.colorScheme.secondaryContainer,
                RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
            .testTag(ImageGalleryTag.ROOT.name),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(image.url)
                .crossfade(true)
                .build(),
            contentDescription = "Gallery Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(64.dp)
                .clip(CircleShape)
                .testTag(ImageGalleryTag.IMAGE.name),
            placeholder = painterResource(id = br.com.khomdrake.request.R.drawable.abc_ic_menu_copy_mtrl_am_alpha)
        )
        Text(
            text = image.date.toString(),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp)
                .testTag(ImageGalleryTag.NAME.name),
            color = MaterialTheme.colorScheme.onSecondaryContainer,
            style = ImperiyaTypography.ParagraphBoldStyle,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun ImageGalleryPreview() {
    ImperiyaTheme {
        BackgroundPreview {
            ImageGallery(
                image = Image(
                    "https://github.com/KhomDrake/CI-Sample",
                    LocalDate.now()
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}