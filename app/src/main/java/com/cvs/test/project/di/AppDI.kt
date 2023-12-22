package com.cvs.test.project.di

import com.cvs.test.project.features.movies.MoviesVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelsModule = module {
    viewModel {
        MoviesVM()
    }
}

val applicationModule = listOf(viewModelsModule)