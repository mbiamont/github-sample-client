package com.mbiamont.github.repository.pullrequests.di

import com.mbiamont.github.domain.feature.repository.details.pullrequests.FetchRepositoryPullRequestsUseCase
import com.mbiamont.github.repository.pullrequests.*
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val pullRequestsModule = module {

    viewModel { PullRequestsViewModel(get()) }

    scope<PullRequestsViewModel> {
        scoped<IPullRequestController> { PullRequestController(get()) }

        scoped<IPullRequestsPresenter> { PullRequestsPresenter(get()) }

        scoped<IPullRequestsViewStateMapper> { PullRequestsViewStateMapper(androidContext()) }

        scoped { PullRequestsInteractor(get(), get()) } bind FetchRepositoryPullRequestsUseCase::class
    }

}