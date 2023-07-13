package com.example.mvvmapp.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mvvmapp.data.model.QuoteModel
import com.example.mvvmapp.data.model.QuoteProvider

class QuoteViewModel: ViewModel() {
    val  quoteModel = MutableLiveData<QuoteModel>()

    fun randomQuote(){
        val currentQuote: QuoteModel = QuoteProvider.random()
        quoteModel.postValue(currentQuote)
    }
}