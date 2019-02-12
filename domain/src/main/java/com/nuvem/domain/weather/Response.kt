package com.nuvem.domain.weather

data class Response<out T> internal constructor(
    val status: Status,

    val message: String? = null,

    val data: T? = null,

    val error: Throwable? = null) {

    enum class Status {
        LOADING, SUCCESS, ERROR
    }

    companion object {
        fun <T> loading(): Response<T> = Response(Status.LOADING)
        fun <T> success(data: T? = null, message: String? = null): Response<T> = Response(Status.SUCCESS, message, data)
        fun <T> error(error: Throwable? = null, message: String? = null, data: T? = null): Response<T> =
            Response(Status.ERROR, message ?: "Generic Error", data, error)

    }

    fun isLoading() = status == Status.LOADING

    fun isSuccess() = status == Status.SUCCESS

    fun isError() = status == Status.ERROR
}