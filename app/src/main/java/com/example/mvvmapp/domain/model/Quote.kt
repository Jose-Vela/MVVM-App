package com.example.mvvmapp.domain.model

import com.example.mvvmapp.data.database.entities.QuoteEntity
import com.example.mvvmapp.data.model.QuoteModel

data class Quote (val quote: String, val author: String)

fun QuoteModel.toDomain() = Quote(quote, author)
fun QuoteEntity.toDomain() = Quote(quote, author)