package com.example.mvvmapp.domain

import com.example.mvvmapp.data.QuoteRepository
import com.example.mvvmapp.domain.model.Quote
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val repository: QuoteRepository) {

    /* En este caso la función invoke no es suspend, ya que no será una corrutina, puesto que el listado lo tenemos almacenado en memoria,
       cuando se tenga en una base de datos, entonces se tendrá que modificar esta función
     */
    suspend operator fun invoke(): Quote? {
        val quotes = repository.getAllQuotesFromDatabase() // Recuperamos el listado
        if (!quotes.isNullOrEmpty()) {    // Validamos que no sea nulo o vacio
            val randomNumber = (quotes.indices).random()   // Generamos un numero aleatorio entre el tamaño del listado
            return quotes[randomNumber] // devolvemos la quote que se encuentre en la posición del numero aleatorio
        }
        return null // Si el listado es nulo o vacio devolvemos null
    }
}