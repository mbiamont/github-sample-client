package com.mbiamont.github.login.di

import com.mbiamont.github.domain.feature.login.FetchOAuthTokenUseCase
import com.mbiamont.github.domain.feature.login.PrepareWebViewUseCase
import com.mbiamont.github.login.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val loginModule = module {

    viewModel { LoginViewModel(get(), get()) }

    single<ILoginPresenter> { LoginPresenter(get()) }

    single<ILoginController> { LoginController(get(), get()) }

    single { LoginInteractor(get(), get(), get()) } bind PrepareWebViewUseCase::class bind FetchOAuthTokenUseCase::class
}