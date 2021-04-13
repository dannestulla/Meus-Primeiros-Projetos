package com.example.noticiasconcursos.util
import kotlinx.coroutines.flow.*

//Essa função é um template, a mesma para vários casos
inline fun <ResultType, RequestType>networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
        saveFetchResult(fetch())
        query().map { Resource.Sucess(it) }
    } catch (tr : Throwable) {
        query().map { Resource.Error(tr,it) }
    }} else { query().map {Resource.Sucess(it) }}
    emitAll(flow)
}