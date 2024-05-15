package com.poatek.gallery.repository

import java.time.LocalDate
import kotlin.random.Random

class Folder(
    val id: Int,
    val name: String,
    val images: List<Image>
)

class Image(
    val url: String,
    val date: LocalDate
)

object GalleryData {

    val gallery: MutableList<Folder> = mutableListOf()
    val imagesUrl = listOf(
        "https://namegame.willowtreeapps.com/images/ameir.jpeg",
        "https://namegame.willowtreeapps.com/images/amer_josh.png",
        "https://namegame.willowtreeapps.com/images/headshot_christine_braynt_ryback.jpg",
        "https://namegame.willowtreeapps.com/images/headshot_margo_bulka.jpg",
        "https://namegame.willowtreeapps.com/images/jarrod.png",
    )

    init {
        repeat(10) {
            val random = Random.nextInt(50, 100)
            val id = it
            val name = "Folder$it"
            var images = mutableListOf<Image>()
            repeat(random) {
                images.add(Image(imagesUrl.random(), LocalDate.now().minusDays(Random.nextLong(0, 30))))
            }
            gallery.add(Folder(id, name, images))
        }
    }

    fun asdada() = 1 + 1

}

class GalleryRepository {

    fun gallery() : List<Folder> {
        return GalleryData.gallery
    }

    fun folder(id: Int) : Folder? {
        return gallery().find { it.id == id }
    }

    fun images() : List<Image> {
        val list = mutableListOf<Image>()

        gallery().forEach {
            list.addAll(it.images)
        }

        return list
    }

    fun byDate(date: LocalDate) : List<Image> {
        return images().filter { it.date == date }
    }

}