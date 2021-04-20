package com.example.noticiasconcursos.util
import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    //declarado como Flow para ficar observando a db
    crossinline query: () -> Flow<ResultType>,                       //consulta a DB
    crossinline shouldFetch: (ResultType) -> Boolean = { true },     //compara os dados online com os da DB
    crossinline fetch: suspend () -> RequestType,                    //busca na API os dados necessários
    crossinline saveFetchResult: suspend (RequestType) -> Unit,      //salva os dados na db

) = flow {
    val lastValue = query().first()

    val flow = if (shouldFetch(lastValue)) {
        emit(Resource.Loading(lastValue))
    try {                                                           //se estiver offline, nao vai buscar
        saveFetchResult(fetch())                                    //salva os dados
            query().map { Resource.Sucess(it) }                     //Trocando o tipo da lista para do tipo query (Flow)
        } catch (tr: Throwable) {
            query().map { Resource.Error(tr, it) }
        }
    } else {
        query().map { Resource.Sucess(it) }
    }
    emitAll(flow)
}