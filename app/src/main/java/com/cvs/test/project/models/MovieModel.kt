package com.cvs.test.project.models

data class MovieModel(
    val title: String,
    val description: String,
    val rating: Double,
    val genre: String,
    val duration: String,
    val releasedData: String,
    val linkTrailer: String,
    val poster: Int,
)