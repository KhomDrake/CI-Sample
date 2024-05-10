package com.poatek.movies

import assertk.all
import assertk.assertThat
import assertk.assertions.index
import assertk.assertions.isEqualTo
import com.poatek.movies.repository.Genre
import com.poatek.movies.repository.MoviesData
import com.poatek.movies.repository.MovieResponse
import com.poatek.movies.repository.MoviesRepository
import org.junit.Test

import org.junit.Before
import java.time.LocalDate

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MoviesRepositoryTest {

    private var defaultData = listOf<MovieResponse>()
    private val today = LocalDate.now()

    @Before
    fun setup() {
        MoviesData.movies.clear()
        defaultData = listOf(
            MovieResponse(
                "1",
                "First",
                today.minusDays(3L),
                Genre.ACTION
            ),
            MovieResponse(
                "2",
                "First 2",
                today.minusDays(3L),
                Genre.COMEDY
            ),
            MovieResponse(
                "3",
                "First 3",
                today.minusDays(1L),
                Genre.DRAMA
            ),
            MovieResponse(
                "4",
                "First 4",
                today.minusDays(4L),
                Genre.ACTION
            ),
        )
        MoviesData.movies.addAll(defaultData)
    }

    @Test
    fun firstTest() = run {
        val moviesRepository = MoviesRepository()
        val result = moviesRepository.movies()

        assertThat(result.size).isEqualTo(4)
        assertThat(result.map { it.genreId }).index(3).isEqualTo(Genre.ACTION)
    }

    @Test
    fun secondTest() = run {
        val moviesRepository = MoviesRepository()
        val result = moviesRepository.moviesByGenre(Genre.ACTION)

        assertThat(result.size).isEqualTo(2)
        result.forEach {
            assertThat(it.genreId).isEqualTo(Genre.ACTION)
        }
    }

    @Test
    fun thirdTest() = run {
        val moviesRepository = MoviesRepository()
        val result = moviesRepository.moviesByGenre(Genre.COMEDY)

        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].id).isEqualTo("2")
        assertThat(result[0].name).isEqualTo("First 2")
    }

    @Test
    fun fourthTest() = run {
        val moviesRepository = MoviesRepository()
        val result = moviesRepository.moviesByDate(today)

        assertThat(result.size).isEqualTo(0)
    }
}