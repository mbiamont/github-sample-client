package com.mbiamont.github.domain.exception

class HttpErrorException(val httpCode: Int, message: String?) : NetworkException(message)