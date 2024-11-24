package com.example.fastfooda1.controllers

import com.example.fastfooda1.models.Address
import com.example.fastfooda1.models.ViaCepService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CepController(private val service: ViaCepService) {

    fun getAddress(cep: String, onSuccess: (Address) -> Unit, onError: () -> Unit) {
        service.getAddress(cep).enqueue(object : Callback<Address> {
            override fun onResponse(call: Call<Address>, response: Response<Address>) {
                response.body()?.let {
                    if (it.erro == true) {
                        onError()
                    } else {
                        onSuccess(it)
                    }
                } ?: onError()
            }

            override fun onFailure(call: Call<Address>, t: Throwable) {
                onError()
            }
        })
    }
}