package com.example.mvvmapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.domain.GetQuotesUseCase
import com.example.mvvmapp.domain.GetRandomQuoteUseCase
import kotlinx.coroutines.launch

class QuoteViewModel: ViewModel() {
    val quoteModel = MutableLiveData<QuoteModel>()
    val isLoading = MutableLiveData<Boolean>()

    var getQuotesUseCase = GetQuotesUseCase()   // Creamos una instancia de nuestro caso de uso GetQuotesUseCase()
    var getRandomQuoteUseCase = GetRandomQuoteUseCase() // Creamos una instancia de nuestro caso de uso GetRandomQuoteUseCase()

    // La función onCreate muestra una primer cita al iniciar la aplicación. Esta funcipon se ejecuta al crearse la MainActivity
    fun onCreate() {
        /* El caso de uso es "suspend" ya que se trata de una corrutina así que para poder lanzarlo desde aquí,
           tendremos que usar ViewModelScope */
        viewModelScope.launch {
            isLoading.postValue(true)   // Mediante el objeto live data, se muestra en pantalla el ProgressBarr para dar feedback
            val result = getQuotesUseCase()  // Obtenemos el resultado que devuelve el caso de uso (de la función invoke)

            if(!result.isNullOrEmpty()){    // Validamos que el resultado mo sea nulo o vacio
                quoteModel.postValue(result[0]) // Enviamos al objeto live data el primer resultado del listado (posisión 0)
                isLoading.postValue(false)  //  Mediante el objeto live data, indicamos que se oculte el ProgressBarr
            }
        }
    }

    fun randomQuote(){
        isLoading.postValue(true)
        val quote: QuoteModel? = getRandomQuoteUseCase()    // Llamamos al caso de uso GetRandomQuoteUseCase()
        if(quote != null){
            quoteModel.postValue(quote!!)
        }
        isLoading.postValue(false)
    }
}