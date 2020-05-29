package com.mbiamont.github.main.di

import com.mbiamont.github.domain.feature.main.FetchUserPublicRepositoriesUseCase
import com.mbiamont.github.main.IMainController
import com.mbiamont.github.main.MainController
import com.mbiamont.github.main.MainInteractor
import com.mbiamont.github.main.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val mainModule = module {

    viewModel { MainViewModel(get(), get()) }

    single<IMainController> { MainController(get()) }

    single { MainInteractor(get()) } bind FetchUserPublicRepositoriesUseCase::class
}