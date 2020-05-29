package com.mbiamont.github.core.qualifier

import org.koin.core.qualifier.qualifier

val bearerTokenQualifier = qualifier("BEARER_TOKEN")

val restQualifier = qualifier("REST")

val graphQlQualifier = qualifier("GRAPH_QL")

val authenticationInterceptorQualifier = qualifier("AUTHENTICATION_INTERCEPTOR")

val githubBaseUrl = qualifier("GITHUB_BASE_URL")