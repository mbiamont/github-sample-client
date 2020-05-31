package com.mbiamont.github.repository.forks.di

import com.mbiamont.github.domain.feature.repository.details.forks.FetchRepositoryForksUseCase
import com.mbiamont.github.domain.feature.repository.details.issues.FetchRepositoryIssuesUseCase
import com.mbiamont.github.repository.forks.*
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val forksModule = module {

    viewModel { ForksViewModel(get()) }

    scope<ForksViewModel> {
        scoped<IForksController> { ForksController(get()) }

        scoped<IForksPresenter> { ForksPresenter() }

        scoped { ForksInteractor(get()) } bind FetchRepositoryForksUseCase::class
    }

}