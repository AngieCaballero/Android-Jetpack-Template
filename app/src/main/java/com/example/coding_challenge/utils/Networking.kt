package com.example.coding_challenge.utils

import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Url



class Networking {

    companion object {


        enum class HTTPMethod {
            GET, POST, PATCH, PUT, DELETE
        }

        private sealed class RequestResult<out T> {
            data class Success<out T>(val data: T) : RequestResult<T>()
            data class Error(val exception: Exception) : RequestResult<Nothing>()
        }

        private interface RequestInterface {

            @GET
            fun <T> get(@Url endpoint: String): Deferred<T>

            fun <T> post(@Url endpoint: String, @Body body: Any): Deferred<T>
        }

        private lateinit var retrofit: RequestInterface

        private fun createRetrofit(baseUrl: String) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RequestInterface::class.java)
        }

        private suspend fun <T> executeRequest(
            baseUrl: String,
            endpoint: String,
            httpMethod: HTTPMethod,
            body: Any? = null
        ): T? {
            if (!::retrofit.isInitialized) {
                createRetrofit(baseUrl)
            }

            return try {
                val response = when (httpMethod) {
                    HTTPMethod.GET -> retrofit.get<T>(endpoint).await()
                    HTTPMethod.POST -> retrofit.post<T>(endpoint, body!!).await()
                    // Add cases for other HTTP methods (PUT, PATCH, DELETE) if needed
                    else -> throw IllegalArgumentException("Unsupported HTTP method: $httpMethod")
                }

                response
            } catch (e: Exception) {
                throw e
            }
        }

        private fun <T> get(endpoint: String): Flow<RequestResult<T>> = flow {
            try {
                val response = retrofit.get<Deferred<T>>(endpoint).await()
                emit(RequestResult.Success(response.await()))
            } catch (e: Exception) {
                emit(RequestResult.Error(e))
            }
        }

        suspend fun <T> request(endpoint: String, base: String, httpMethod: HTTPMethod): T? {

            return executeRequest(base, endpoint, httpMethod)
        }
    }
}


