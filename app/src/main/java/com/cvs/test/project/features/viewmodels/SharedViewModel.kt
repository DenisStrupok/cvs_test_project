package com.cvs.test.project.features.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cvs.test.project.R
import com.cvs.test.project.features.movies.MoviesFragment
import com.cvs.test.project.models.MovieModel

class SharedViewModel : ViewModel() {

    private val _listMovies = MutableLiveData<MutableList<MovieModel>>().apply {
        value = mutableListOf()
    }
    val listMovies: LiveData<MutableList<MovieModel>> = _listMovies

    private val _movie = MutableLiveData<MovieModel>()
    val movie: LiveData<MovieModel> = _movie

    private val _isAdded = MutableLiveData(false)
    val isAdd: LiveData<Boolean> = _isAdded

    fun getMovie(title: String) {
        _movie.value = listMovies.value?.find { it.title == title }
    }


    fun sortList(type: String) {
        when (type) {
            MoviesFragment.SORT_TYPE_TITLE -> {
                _listMovies.value?.sortBy { it.title }
                _listMovies.value = _listMovies.value
            }

            MoviesFragment.SORT_TYPE_DATA -> {
                _listMovies.value?.sortBy { it.releasedData }
                _listMovies.value = _listMovies.value
            }
        }
    }

    fun removeFromList() {
        _movie.value?.isWatched = false
        _isAdded.value = false
    }

    fun addToList() {
        _movie.value?.isWatched = true
        _isAdded.value = true
    }


    fun getListMovies() {
        if (listMovies.value.isNullOrEmpty()) {
            _listMovies.value = mutableListOf<MovieModel>().apply {
                this.add(
                    MovieModel(
                        "Tenet",
                        "Armed with only one word, Tenet, and fighting for the survival of the entire world, a Protagonist journeys through a twilight world of\n" +
                                "international espionage on a mission that will unfold in something beyond real time.",
                        7.8,
                        "Action, Sci-Fi",
                        "2h 30 min",
                        "3 September 2020",
                        "https://www.youtube.com/watch?v=LdOM0x0XDMo",
                        R.drawable.tenet
                    )
                )
                this.add(
                    MovieModel(
                        "Spider Man",
                        "Teen Miles Morales becomes the Spider-Man of his universe, and must join with five spider-powered individuals from other\n" +
                                "dimensions to stop a threat for all realities.",
                        8.4,
                        "Action, Animation, Adventure",
                        "1h 57min",
                        "14 December 2018",
                        "https://www.youtube.com/watch?v=tg52up16eq0",
                        R.drawable.spider_man
                    )
                )
                this.add(
                    MovieModel(
                        "Knives Out",
                        "A detective investigates the death of a patriarch of an eccentric, combative family.",
                        7.9,
                        "Comedy, Crime, Drama",
                        "2h 10min",
                        "27 November 2019",
                        "https://www.youtube.com/watch?v=qGqiHJTsRkQ",
                        R.drawable.knives_out
                    )
                )
                this.add(
                    MovieModel(
                        "Guardians of the Galaxy",
                        "A group of intergalactic criminals must pull together to stop a fanatical warrior with plans to purge the universe.\n",
                        8.0,
                        "Action, Adventure, Comedy",
                        "2h 1min",
                        "1 August 2014",
                        "https://www.youtube.com/watch?v=d96cjJhvlMA",
                        R.drawable.guardians_of_the_galaxy
                    )
                )
                this.add(
                    MovieModel(
                        "Avengers: Age of Ultron",
                        "When Tony Stark and Bruce Banner try to jump-start a dormant peacekeeping program called Ultron, things go horribly wrong and\n" +
                                "up to Earth+ mightiest heroes to stop the villainous Ultron from enacting his terrible plan.",
                        7.3,
                        "Action, Adventure, Sci-Fi",
                        "2h 21min",
                        "1 May 2015",
                        "https://www.youtube.com/watch?v=tmeOjFno6Do",
                        R.drawable.avengers
                    )
                )
            }
        }
    }
}