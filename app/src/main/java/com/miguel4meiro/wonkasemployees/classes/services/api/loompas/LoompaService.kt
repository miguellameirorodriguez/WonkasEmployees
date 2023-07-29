package com.miguel4meiro.wonkasemployees.classes.services.api.loompas

import com.miguel4meiro.wonkasemployees.classes.models.api.LoompaDto
import com.miguel4meiro.wonkasemployees.classes.models.api.LoompasResponseDto
import com.miguel4meiro.wonkasemployees.classes.services.ApiServiceInterface
import retrofit2.Response
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class LoompaService(
    private val apiService: ApiServiceInterface
): LoompaServiceInterface {

    override suspend fun getLoompas(page: Int): LoompasResponseDto {
        val response = apiService.getLoompas(page)

        return resumeResponse(response)
    }

    override suspend fun getLoompa(id: Int): LoompaDto {
        val response = apiService.getLoompa(id)

        return resumeResponse(response)
    }

    private suspend fun <T> resumeResponse(response: Response<T>): T {
        return suspendCoroutine { continuation ->
            val body = response.body()
            if (response.isSuccessful && body != null) {
                continuation.resumeWith(Result.success(body))
            } else {
                continuation.resumeWithException(Exception("There's been an error while fetching loompas"))
            }
        }
    }
}