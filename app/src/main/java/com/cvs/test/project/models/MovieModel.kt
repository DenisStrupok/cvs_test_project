package com.cvs.test.project.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MovieModel(
    val title: String,
    val description: String,
    val rating: Double,
    val genre: String,
    val duration: String,
    val releasedData: String,
    val linkTrailer: String,
    val poster: Int,
    var isWatched: Boolean = false
): Parcelable