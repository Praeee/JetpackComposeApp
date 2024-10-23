package com.praeee.jetpackcomposeapp.util

sealed class ResourceState<T> {

    class Loading<T> : ResourceState<T>()
    data class Success<T>(val data: T) : ResourceState<T>()
    data class Error<T>(val error: String) : ResourceState<T>()

}

data class Result<T>(val isSuccess: Boolean, val message: String?, val data: T?) {
    companion object {
        fun <T> success(data: T?): Result<T> = Result(true, null, data)
        fun <T> error(message: String?): Result<T> = Result(false, message, null)
    }
}