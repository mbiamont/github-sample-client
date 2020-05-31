package com.mbiamont.github.repository.forks.di

import com.mbiamont.github.domain.feature.repository.details.forks.FetchRepositoryForksUseCase
import com.mbiamont.github.domain.feature.repository.details.issues.FetchRepositoryIssuesUseCase
import com.mbiamont.github.repository.forks.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val forksModule = module {

    viewModel { ForksViewModel(get()) }

    scope<ForksViewModel> {
        scoped<IForksController> { ForksController(get()) }

        scoped<IForksPresenter> { ForksPresenter(get()) }

        scoped<IForksViewStateMapper> { ForksViewStateMapper(androidContext()) }

        scoped { ForksInteractor(get(), get()) } bind FetchRepositoryForksUseCase::class
    }

}