package com.example.mvvmapp.data

import com.example.mvvmapp.data.database.dao.QuoteDao
import com.example.mvvmapp.data.database.entities.QuoteEntity
import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.data.network.QuoteService
import com.example.mvvmapp.domain.model.Quote
import com.example.mvvmapp.domain.model.toDomain
import javax.inject.Inject

class QuoteRepository @Inject constructor(
    private val api: QuoteService,
    private val quoteDao: QuoteDao
) {

    suspend fun getAllQuotesFromApi(): List<Quote> {
        // Llamaos al backend recuperando el listado total de las citas
        val response: List<QuoteModel> = api.getQuotes()
        // Retornamos la respuesta
        return response.map { it.toDomain() }   // Mapeamos el resultado a tipo Quote
    }

    suspend fun getAllQuotesFromDatabase(): List<Quote>{
        val response: List<QuoteEntity> = quoteDao.getAllQuotes()
        return response.map { it.toDomain() }
    }

    suspend fun insertQuotes(quotes: List<QuoteEntity>){
        quoteDao.insertAll(quotes)
    }

    suspend fun clearQuotes(){
        quoteDao.deleteAllQuotes()
    }
}