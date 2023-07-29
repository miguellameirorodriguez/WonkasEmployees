package com.miguel4meiro.wonkasemployees.classes.services

import com.miguel4meiro.wonkasemployees.classes.models.api.LoompaDto
import com.miguel4meiro.wonkasemployees.classes.models.api.LoompasResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiServiceInterface {

    @GET("oompa-loompas")
    suspend fun getLoompas(@Query("page") page: Int): Response<LoompasResponseDto>

    @GET("oompa-loompas/{id}")
    suspend fun getLoompa(@Path("id") id: Int): Response<LoompaDto>
}