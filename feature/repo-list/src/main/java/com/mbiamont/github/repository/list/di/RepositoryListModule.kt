package com.mbiamont.github.repository.list.di

import com.mbiamont.github.domain.feature.repository.list.FetchUserPublicRepositoriesUseCase
import com.mbiamont.github.repository.list.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryListModule = module {

    viewModel { RepositoryListViewModel(get(), get(), get()) }

    single<IRepositoryListViewStateMapper> { RepositoryListViewStateMapper() }

    single<IRepositoryListController> { RepositoryListController(get(), get()) }

    single<IRepositoryListPresenter> { RepositoryListPresenter(get()) }

    single { RepositoryListInteractor(get(), get()) } bind FetchUserPublicRepositoriesUseCase::class
}