package com.mbiamont.github.splashscreen.di

import com.mbiamont.github.domain.feature.splashscreen.VerifyUserSessionUseCase
import com.mbiamont.github.splashscreen.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val splashModule = module {

    viewModel { SplashViewModel(get()) }

    single<ISplashController> { SplashController(get()) }

    single<ISplashPresenter> { SplashPresenter(get()) }

    single<VerifyUserSessionUseCase> { SplashInteractor(get(), get()) }
}