package com.mbiamont.github.repository.details.di

import com.mbiamont.github.domain.feature.repository.details.FetchRepositoryDetailsUseCase
import com.mbiamont.github.repository.details.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryDetailsModule = module {

    viewModel { RepositoryDetailsViewModel(get(), get(), get()) }

    single<IRepositoryDetailsViewStateMapper> { RepositoryDetailsViewStateMapper(get()) }

    single<IRepositoryDetailsController> { RepositoryDetailsController(get()) }

    single<IRepositoryDetailsPresenter> { RepositoryDetailsPresenter(get()) }

    single { RepositoryDetailsInteractor(get(), get()) } bind FetchRepositoryDetailsUseCase::class

}