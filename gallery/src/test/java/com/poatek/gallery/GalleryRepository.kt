package com.poatek.gallery

import assertk.assertions.isEqualTo
import com.poatek.gallery.repository.Folder
import com.poatek.gallery.repository.GalleryData
import com.poatek.gallery.repository.GalleryRepository
import com.poatek.gallery.repository.Image
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import java.time.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GalleryRepository {

    lateinit var repository: GalleryRepository
    private val today = LocalDate.now()

    @Before
    fun setup() {
        GalleryData.gallery.clear()
        GalleryData.gallery
            .apply {
                var asd = 0
                repeat(10) {
                    val name = "name-$it"
                    val id = it
                    val images = mutableListOf<Image>()
                    repeat(if(it == 3) 4 else 5) { index ->
                        images.add(
                            Image(
                                GalleryData.imagesUrl[index],
                                LocalDate.now().minusDays(index.toLong())
                            )
                        )
                        asd++
                    }
                    add(Folder(id, name, images))
                }
                println(asd)
            }

        repository = GalleryRepository()
    }

    @Test
    fun requestAllImages_shouldReturnRightAmount() = runTest {
        assertk.assertThat(repository.images().size).isEqualTo(49)
    }

    @Test
    fun requestImagesFromToday_shouldReturnRightAmount() = runTest {
        assertk.assertThat(repository.byDate(today).size).isEqualTo(10)
    }
}