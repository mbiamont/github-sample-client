package com.mbiamont.github.repository.pullrequests.di

import com.mbiamont.github.domain.feature.repository.details.pullrequests.FetchRepositoryPullRequestsUseCase
import com.mbiamont.github.repository.pullrequests.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val pullRequestsModule = module {

    viewModel { PullRequestsViewModel(get(), get(), get()) }

    single<IPullRequestController> { PullRequestController(get()) }

    single<IPullRequestsPresenter> { PullRequestsPresenter(get()) }

    single<IPullRequestsViewStateMapper> { PullRequestsViewStateMapper(androidContext(), get()) }

    single { PullRequestsInteractor(get(), get()) } bind FetchRepositoryPullRequestsUseCase::class

}