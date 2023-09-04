package com.josearroyo.comparatexto

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.josearroyo.comparatexto.R.id
import com.josearroyo.comparatexto.R.layout


// ViewModel
class CompareStringsViewModel : ViewModel() {
    private val _comparisonResult = MutableLiveData<String>()
    val comparisonResult: LiveData<String> = _comparisonResult

    fun compareStrings(text1: String, text2: String) {
        if (text1 == text2) {
            _comparisonResult.value = "Las cadenas son iguales"
        } else {
            _comparisonResult.value = "Las cadenas son diferentes"
        }
    }
}

// MainActivity
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CompareStringsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        this.viewModel = ViewModelProvider(this).get(CompareStringsViewModel::class.java)

        val botonPulsado = findViewById<AppCompatButton>(id.btnPulsado)
        val primerTexto = findViewById<AppCompatEditText>(id.primerTexto)
        val segundoTexto = findViewById<AppCompatEditText>(id.segundoTexto)
        val resultadoComp = findViewById<AppCompatTextView>(id.resultadoComp)

        botonPulsado.setOnClickListener {
            val text1 = primerTexto.text.toString()
            val text2 = segundoTexto.text.toString()

            this.viewModel.compareStrings(text1, text2)
        }

        this.viewModel.comparisonResult.observe( this) { resultadoComp.text = it }
    }
}
