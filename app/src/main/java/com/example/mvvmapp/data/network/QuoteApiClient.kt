package com.example.mvvmapp.data.network

import com.example.mvvmapp.data.model.QuoteModel
import retrofit2.Response
import retrofit2.http.GET

interface QuoteApiClient {
    // Se trata de una petición get que nos devolverá un listado de QuoteModel.
    @GET("/.json")
    // Se trata de una función suspend ya que será a través de una corrutina.
    suspend fun getAllQuotes(): Response<List<QuoteModel>>
}