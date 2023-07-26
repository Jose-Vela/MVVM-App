package com.example.mvvmapp.domain

import com.example.mvvmapp.data.QuoteRepository
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.RelaxedMockK
import org.junit.Before


internal class GetQuotesUseCaseTest{

    //@MockK      // Tendremos que configurar cada una de las respuestas que pudiera darnos esa clase mockeada.
    @RelaxedMockK       // Si no controlamos la respuesta de una de sus funciones el propio sistema nos dará una por defecto
    private lateinit var quoteRepository: QuoteRepository

    lateinit var getQuotesUseCase: GetQuotesUseCase

    @Before     // Se inicializará antes de lanzar los tests
    fun onBefore(){
        MockKAnnotations.init(this)
        getQuotesUseCase = GetQuotesUseCase(quoteRepository)
    }
}