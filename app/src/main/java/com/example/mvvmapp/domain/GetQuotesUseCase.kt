package com.example.mvvmapp.domain

import com.example.mvvmapp.data.QuoteRepository
import com.example.mvvmapp.data.database.entities.toDatabase
import com.example.mvvmapp.domain.model.Quote
import javax.inject.Inject

/* Este sería el caso de uso más básico, el cual solo llama al repositorio para decirle que recupere de internet todas las citas
 */

class GetQuotesUseCase @Inject constructor(private val repository: QuoteRepository) {


    /* La siguiente función es algo extraña ya que con el operator invoke podemos llamarla sin tener que darle un nombre,
       es decir, con hacer GetQuotesUseCase() ya se estaría llamando,
       similar a un constructor pero sin tener que pasarle los parámetros.
     */
    suspend operator fun invoke(): List<Quote>{
        val quotes = repository.getAllQuotesFromApi()

        return if(quotes.isNotEmpty()){
            repository.clearQuotes()
            repository.insertQuotes(quotes.map { it.toDatabase() })
            quotes
        } else {
            repository.getAllQuotesFromDatabase()
        }
    }
}