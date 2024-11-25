package com.example.fastfooda1.models

import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepService {
    @GET("{cep}/json/")
    suspend fun getAddress(@Path("cep") cep: String): ViaCepResponse
}