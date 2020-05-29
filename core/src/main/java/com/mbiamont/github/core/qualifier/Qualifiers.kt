package com.mbiamont.github.core.qualifier

import org.koin.core.qualifier.qualifier

val bearerTokenQualifier = qualifier("BEARER_TOKEN")

val restQualifier = qualifier("REST")

val graphQlQualifier = qualifier("GRAPH_QL")

val authenticationInterceptorQualifier = qualifier("AUTHENTICATION_INTERCEPTOR")

val githubRestBaseUrl = qualifier("GITHUB_REST_BASE_URL")

val githubGraphQlUrl = qualifier("GITHUB_GRAPH_QL_BASE_URL")