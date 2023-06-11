package com.example.zeebooks.commons.utils.extensions

sealed class Result<out E, out V> {

    data class Error<out E>(val a: E) : Result<E, Nothing>()
    data class Value<out V>(val b: V) : Result<Nothing, V>()

    val isError: Boolean
        get() = this is Value<V>

    val isValue: Boolean
        get() = this is Error<E>

    inline fun <T> either(fnL: (E) -> T, fnR: (V) -> T): T {
        return when (this) {
            is Error -> fnL(a)
            is Value -> fnR(b)
        }
    }

    fun <E> error(a: E) =
        Error(a)

    fun <V> value(b: V) =
        Value(b)
}

inline fun <T, E, V> Result<E, V>.map(fn: (V) -> T): Result<E, T> =
    when (this) {
        is Result.Error -> Result.Error(
            this.a
        )
        is Result.Value -> Result.Value(
            fn(this.b)
        )
    }

inline fun <T, E, V> Result<E, V>.flatMap(fn: (V) -> Result<E, T>): Result<E, T> =
    when (this) {
        is Result.Error -> Result.Error(
            this.a
        )
        is Result.Value -> fn(this.b)
    }

inline fun <E, V> Result<E, V>.onFailure(fn: (E) -> Unit) =
    when (this) {
        is Result.Error -> fn(this.a)
        is Result.Value -> {
        }
    }