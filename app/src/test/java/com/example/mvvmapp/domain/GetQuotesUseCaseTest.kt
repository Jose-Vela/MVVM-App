package com.example.mvvmapp.domain

import com.example.mvvmapp.data.QuoteRepository
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.impl.annotations.RelaxedMockK
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test


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

    @Test
    fun `when the api doesnt return anything then get values from database`() = runBlocking {
        // Given:
        coEvery { quoteRepository.getAllQuotesFromApi() } returns emptyList()

        // When:
        getQuotesUseCase()

        // Then
        coVerify(exactly = 1) { quoteRepository.getAllQuotesFromDatabase() }
    }
}