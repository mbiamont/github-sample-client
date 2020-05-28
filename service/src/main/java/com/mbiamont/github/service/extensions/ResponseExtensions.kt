package com.mbiamont.github.service.extensions

import com.mbiamont.github.domain.exception.HttpErrorException
import com.mbiamont.github.domain.exception.NetworkException
import retrofit2.Response

fun <T> Response<T>.networkException(): NetworkException = HttpErrorException(code(), errorBody()?.toString())