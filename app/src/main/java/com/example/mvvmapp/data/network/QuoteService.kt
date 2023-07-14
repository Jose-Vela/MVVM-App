package com.example.mvvmapp.data.network

import com.example.mvvmapp.core.RetrofitHelper
import com.example.mvvmapp.data.model.QuoteModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/* Esta clase llamada QuoteService, será la puerta de acceso a internet, la cual será llamada por el repositorio (QuoteRepository)
   cuando queramos datos de internet y esta misma clase ya gestionaría la llamada a Retrofit o a Firebase por ejemplo.

   Con esta clase conseguimos abstraer la parte de Retrofit al máximo, es decir, si un día queremos cambiar los endpoints,
   solo deberemos tocar esta clase y el resto de nuestra app quedará intacta.
 */

class QuoteService {

    private val retrofit = RetrofitHelper.getRetrofit()

    /* Dentro de la función getQuotes() estamos creando una corrutina de tipo IO,
       que serán las óptimas para hacer llamadas de red o a bases de datos y esto retornará lo que se haga dentro.
    */
    suspend fun getQuotes(): List<QuoteModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(QuoteApiClient::class.java).getAllQuotes()
            response.body() ?: emptyList()
        }
    }
}