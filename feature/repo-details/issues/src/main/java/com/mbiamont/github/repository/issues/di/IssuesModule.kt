package com.mbiamont.github.repository.issues.di

import com.mbiamont.github.domain.feature.repository.details.issues.FetchRepositoryIssuesUseCase
import com.mbiamont.github.repository.issues.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val issuesModule = module {

    viewModel { IssuesViewModel(get(), get(), get()) }

    single<IIssuesViewStateMapper> { IssuesViewStateMapper(androidContext(), get()) }

    single<IIssuesController> { IssuesController(get()) }

    single<IIssuesPresenter> { IssuesPresenter(get()) }

    single { IssuesInteractor(get(), get()) } bind FetchRepositoryIssuesUseCase::class

}