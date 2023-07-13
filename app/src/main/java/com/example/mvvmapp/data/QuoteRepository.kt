package com.example.mvvmapp.data

import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.data.model.QuoteProvider
import com.example.mvvmapp.data.network.QuoteService

class QuoteRepository {

    private val api = QuoteService()

    suspend fun getAllQuotes():List<QuoteModel>{
        // Llamaos al backend recuperando el listado total de las citas
        val response: List<QuoteModel> = api.getQuotes()
        // Almacenamos en memoria (a modo de "base de datos") la respuesta (response) del servidor/backend, en la variable QuoteProvider.quotes
        QuoteProvider.quotes = response
        // Retornamos la respuesta
        return response
    }
}