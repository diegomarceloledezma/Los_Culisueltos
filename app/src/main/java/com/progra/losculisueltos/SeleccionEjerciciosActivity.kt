package com.progra.losculisueltos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.adapter.SeleccionEjereciciosAdapter
import com.progra.losculisueltos.databinding.ActivitySeleccionEjerciciosBinding
import com.progra.losculisueltos.dataclases.Ejercicios

class SeleccionEjerciciosActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySeleccionEjerciciosBinding
    private val seleccionEjereciciosAdapter by lazy{ SeleccionEjereciciosAdapter() }
    val ejerciciosBase = ListaDeEjerciciosProvider()
    val ejercicioPierna = ejerciciosBase.listaPiernas
    val ejercicioPecho = ejerciciosBase.listaPecho
    val ejercicioEspalda = ejerciciosBase.listaEspalda
    val ejercicioBrazo = ejerciciosBase.listaBrazos
    val ejercicioHombros = ejerciciosBase.listaHombros
    val ejercicioAbdominal = ejerciciosBase.listaAbdominales
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionEjerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciarRecyclerViewPiernas(ejercicioPierna)
    }

    private fun iniciarRecyclerViewPiernas(ejercicios: List<Ejercicios>) {

        seleccionEjereciciosAdapter.addListaEjercicios(ejercicios)

        binding.piernasRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = seleccionEjereciciosAdapter
            seleccionEjereciciosAdapter.setOnItemClickListener { ejercicio ->

            }
        }
        seleccionEjereciciosAdapter.notifyDataSetChanged()
    }
}