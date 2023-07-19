package com.example.mvvmapp.data

import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.data.model.QuoteProvider
import com.example.mvvmapp.data.network.QuoteService
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteProvider: QuoteProvider
) {

    suspend fun getAllQuotes(): List<QuoteModel> {
        // Llamaos al backend recuperando el listado total de las citas
        val response: List<QuoteModel> = api.getQuotes()
        // Almacenamos en memoria (a modo de "base de datos") la respuesta (response) del servidor/backend, en la variable QuoteProvider.quotes
        quoteProvider.quotes = response
        // Retornamos la respuesta
        return response
    }
}