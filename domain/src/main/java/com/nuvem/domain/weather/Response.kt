package com.nuvem.domain.weather

data class Response<out T> internal constructor(
    val status: Status,

    val message: Int? = null,

    val data: T? = null,

    val error: Throwable? = null) {

    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <T> loading(): Response<T> = Response(Status.LOADING)
        fun <T> success(data: T? = null, message: Int? = null): Response<T> = Response(Status.SUCCESS, message, data)
        fun <T> error(error: Throwable? = null, message: Int? = null, data: T? = null): Response<T> =
            Response(Status.ERROR, message, data, error)

    }

    fun isLoading() = status == Status.LOADING

    fun isSuccess() = status == Status.SUCCESS

    fun isError() = status == Status.ERROR
}