package com.example.fastfooda1.models

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ViaCepService {
    @GET("ws/{cep}/json/")
    fun getAddress(@Path("cep") cep: String): Call<Address>
}