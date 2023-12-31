package com.example.mvvmapp.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.example.mvvmapp.databinding.ActivityMainBinding
import com.example.mvvmapp.ui.viewmodel.QuoteViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    private val quoteViewModel: QuoteViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        quoteViewModel.onCreate()   // Llamamos a la función onCreate del QuoteViewModel

        // Live data es básicamente el PATRÓN OBSERVE.
        // Lo que esté dentro de "quoteViewModel.quoteModel.observe", se ejecutará automáticamente cada vez que nuestro objeto con live data sea modificado
        quoteViewModel.quoteModel.observe(this, Observer{ currentQuote ->
            binding.tvQuote.text = currentQuote.quote
            binding.tvAuthor.text = currentQuote.author
        })
        // --------------------------------------------------------------------

        // Este objeto live data es para controlar la visibilidad del ProgressBar
        quoteViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })

        // El objeto con live data (quoteViewModel.quoteModel) se modifica al llamar a la función randomQuote() del ViewModel.
        // Para ello añadimos un setOnClickListener al constraintLayout principal. Así cada vez que tocamos la pantalla actualizaremos la cita
        binding.viewContainer.setOnClickListener { quoteViewModel.randomQuote() }
    }
}