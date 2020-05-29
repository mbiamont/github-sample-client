package com.mbiamont.github.main.di

import com.mbiamont.github.domain.feature.main.FetchUserPublicRepositoriesUseCase
import com.mbiamont.github.main.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val mainModule = module {

    viewModel { MainViewModel(get(), get(), get()) }

    single<IMainViewStateMapper> { MainViewStateMapper() }

    single<IMainController> { MainController(get()) }

    single<IMainPresenter> { MainPresenter(get()) }

    single { MainInteractor(get(), get(), get()) } bind FetchUserPublicRepositoriesUseCase::class
}