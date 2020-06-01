package com.mbiamont.github.core

/**
 * Similar class to kotlin.Result
 * But the original version has unsolved bug: https://youtrack.jetbrains.com/issue/KT-27586
 */
sealed class Monad<out T : Any> {

    val isSuccess: Boolean
        get() = this is Success

    val isFailure: Boolean
        get() = this is Failure

    data class Success<T : Any>(val value: T) : Monad<T>()
    data class Failure(val error: Throwable) : Monad<Nothing>()
}

/**
 * Create a Success instance
 *
 * Usage:
 *
 * val result: Monad<String> = success("Hello World")
 */
fun <T : Any> success(value: T) = Monad.Success(value)

/**
 * Create a Failure instance
 *
 * Usage:
 *
 * val failure = failure(RuntimeException(), "boom", null)
 */
fun failure(error: Throwable) = Monad.Failure(error)

/**
 * Return the Success value or null if it's a Failure
 *
 * Usage:
 *
 * val result: Monad<String> = success("Hello World")
 * result.successOrNull()?.let {
 *    println(it)
 * }
 */
fun <T : Any> Monad<T>.successOrNull(): T? = (this as? Monad.Success)?.value

/**
 * Return the Success value or the specified [default] value if it's a Failure
 *
 * Usage:
 *
 * val result: Monad<String> = success("Hello World")
 * val response = result.successOrDefault("Default value")
 * println(response)
 */
fun <T : Any> Monad<T>.successOrDefault(default: T): T = (this as? Monad.Success)?.value ?: default

/**
 * Calls the specified function [onSuccess] with `this` value as its receiver if it's a Success and returns the Monad
 *
 * Usage:
 *
 * val result: Monad<String> = success("Hello World")
 * result.onSuccess {
 *    println(it)
 * }
 */
inline fun <T : Any, R> Monad<T>.onSuccess(onSuccess: (value: T) -> R): Monad<T> {
    if (this is Monad.Success) {
        onSuccess(this.value)
    }
    return this
}

/**
 * Calls the specified function [onFailure] with `this` value as its receiver if it's a Failure and returns the Monad
 *
 * Usage:
 *
 * val result: Monad<String> = success("Hello World")
 * result.onFailure { error, message, type ->
 *    error.printStackTrace()
 * }
 */
inline fun <T : Any, R> Monad<T>.onFailure(onFailure: (error: Throwable) -> R): Monad<T> {
    if (this is Monad.Failure) {
        onFailure(this.error)
    }
    return this
}


/**
 * Map the Monad value using the [transform] lambda (only if it's a Success)
 *
 * Usage:
 *
 * val result: Monad<String> = success(123).map { it.toString() }
 * result.onSuccess {
 *    println(it)
 * }
 */
inline fun <R : Any, T : Any> Monad<T>.map(transform: (value: T) -> R): Monad<R> = when {
    isSuccess -> success(transform((this as Monad.Success).value))
    else -> failure((this as Monad.Failure).error)
}

/**
 * Execute the specified function [block] inside a try/catch block and return a Failure if an exception is raised otherwise, return a Success
 *
 * Usage:
 *
 * val result = runSafe {
 *   "Hello World"
 * }
 *
 * result.onSuccess {
 *    println(it)
 * }.onFailure { error, message, type ->
 *    error.printStackTrace()
 * }
 *
 * when(result){
 *    is Success -> println(result.value)
 *    is Failure -> result.error.printStackTrace()
 * }
 */
inline fun <T, R : Any> T.runSafe(block: T.() -> R): Monad<R> {
    return try {
        success(block())
    } catch (e: Throwable) {
        failure(e)
    }
}