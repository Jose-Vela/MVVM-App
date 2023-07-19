package com.example.mvvmapp.domain

import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.data.model.QuoteProvider
import javax.inject.Inject

class GetRandomQuoteUseCase @Inject constructor(private val quoteProvider: QuoteProvider) {

    /* En este caso la función invoke no es suspend, ya que no será una corrutina, puesto que el listado lo tenemos almacenado en memoria,
       cuando se tenga en una base de datos, entonces se tendrá que modificar esta función
     */
    operator fun invoke(): QuoteModel? {
        val quotes: List<QuoteModel> = quoteProvider.quotes // Recuperamos el listado
        if (!quotes.isNullOrEmpty()) {    // Validamos que no sea nulo o vacio
            val randomNumber: Int =
                (quotes.indices).random()   // Generamos un numero aleatorio entre el tamaño del listado
            return quotes[randomNumber] // devolvemos la quote que se encuentre en la posición del numero aleatorio
        }
        return null // Si el listado es nulo o vacio devolvemos null
    }
}