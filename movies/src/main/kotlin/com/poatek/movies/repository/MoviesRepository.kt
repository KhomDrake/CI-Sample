package com.poatek.movies.repository

import java.time.LocalDate
import java.util.UUID
import kotlin.random.Random

enum class Genre {
    FANTASY,
    SCIFI,
    COMEDY,
    ACTION,
    DRAMA
}

class MovieResponse(
    val id: String,
    val name: String,
    val date: LocalDate,
    val genreId: Genre
)

object MoviesData {

    val movies = mutableListOf<MovieResponse>()

    init {
        val date = LocalDate.now()
        repeat(Random.nextInt(100, 200)) {
            val index = Random.nextInt(0, Genre.entries.size)
            val shouldDecreaseDate = Random.nextInt(0, 10) >= 5
            movies.add(
                MovieResponse(
                    UUID.randomUUID().toString(),
                    "Test $it",
                    date,
                    Genre.entries[index]
                )
            )
            if(shouldDecreaseDate) date.minusDays(1L)
        }
    }

}

class MoviesRepository {

    fun moviesByGenre(genreId: Genre) = movies()
        .filter { it.genreId == genreId }

    fun moviesByDate(date: LocalDate) = movies()
        .filter { it.date == date }

    fun movies() = MoviesData.movies

}