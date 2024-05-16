package com.poatek.gallery

import com.poatek.gallery.repository.Folder
import com.poatek.gallery.repository.GalleryData
import com.poatek.gallery.repository.GalleryRepository
import com.poatek.gallery.repository.Image
import org.junit.Before
import org.junit.Test
import java.time.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class GalleryRepositoryTest {

    lateinit var repository: GalleryRepository
    private val today = LocalDate.now()

    @Before
    fun setup() {
        GalleryData.gallery.clear()
        GalleryData.gallery
            .apply {
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
                    }
                    add(Folder(id, name, images))
                }
            }

        repository = GalleryRepository()
    }

    @Test
    fun requestAllImages_shouldReturnRightAmount() {
        // here just to check sure is running unit tests
        Thread.sleep(5000)
        assert(repository.images().size == 49)
    }

    @Test
    fun requestImagesFromToday_shouldReturnRightAmount() {
        assert(repository.byDate(today).size == 10)
    }

    @Test
    fun testing2() {
        assert(GalleryData.asdada() == 2)
    }
}