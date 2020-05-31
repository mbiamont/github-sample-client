package com.mbiamont.github.repository.issues.di

import com.mbiamont.github.domain.feature.repository.details.issues.FetchRepositoryIssuesUseCase
import com.mbiamont.github.repository.issues.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val issuesModule = module {

    viewModel { IssuesViewModel(get()) }

    scope<IssuesViewModel> {

        scoped<IIssuesViewStateMapper> { IssuesViewStateMapper(androidContext()) }

        scoped<IIssuesController> { IssuesController(get()) }

        scoped<IIssuesPresenter> { IssuesPresenter(get()) }

        scoped { IssuesInteractor(get(), get()) } bind FetchRepositoryIssuesUseCase::class
    }

}