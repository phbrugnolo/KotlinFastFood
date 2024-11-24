package com.example.fastfooda1.controllers

//import com.example.fastfooda1.models.ViaCepResponse
//import com.example.fastfooda1.models.ViaCepService
//import retrofit2.Call
//import retrofit2.Callback
//import retrofit2.Response
//
//class CepController(private val service: ViaCepService) {
//
//    fun getAddress(cep: String, onSuccess: (Address) -> Unit, onError: () -> Unit) {
//        service.getAddress(cep).enqueue(object : Callback<ViaCepResponse> {
//            override fun onResponse(call: Call<ViaCepResponse>, response: Response<ViaCepResponse>) {
//                response.body()?.let { viaCep ->
//                    if (viaCep.erro == true) {
//                        onError()
//                    } else {
//                        val address = Address(
//                            id = 0,
//                            customerId = 0,
//                            cep = viaCep.cep,
//                            logradouro = viaCep.logradouro,
//                            complemento = viaCep.complemento,
//                            bairro = viaCep.bairro,
//                            localidade = viaCep.localidade,
//                            uf = viaCep.uf,
//                            estado = viaCep.estado
//                        )
//                        onSuccess(address)
//                    }
//                } ?: onError()
//            }
//
//            override fun onFailure(call: Call<ViaCepResponse>, t: Throwable) {
//                onError()
//            }
//        })
//    }
//}