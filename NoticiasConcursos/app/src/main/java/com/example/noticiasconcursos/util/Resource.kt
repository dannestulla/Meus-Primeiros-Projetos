package com.example.noticiasconcursos.util

sealed class Resource<T>(
        val data: T? = null,
        val error: Throwable? = null
) {
    class Sucess<T>(data: T) : Resource<T>()
    class Loading<T>(data: T? =null) : Resource<T>()
    class Error<T>(tr : Throwable, data : T? =null) : Resource<T>(data, tr)
}