package com.cvs.test.project.di

import com.cvs.test.project.features.viewmodels.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelsModule = module {
    viewModel {
        SharedViewModel()
    }
}

val applicationModule = listOf(viewModelsModule)