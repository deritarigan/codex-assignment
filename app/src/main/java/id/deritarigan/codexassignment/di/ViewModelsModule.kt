package id.deritarigan.codexassignment.di

import id.deritarigan.codexassignment.ui.viewmodel.StoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

object ViewModelsModule {

    val module = module {
        viewModel { StoriesViewModel(get()) }
    }
}