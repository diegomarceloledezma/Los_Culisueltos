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
    val mapaEjercicios: MutableMap<Int, Boolean> = mutableMapOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionEjerciciosBinding.inflate(layoutInflater)
        for(i in 1 .. 36){
            mapaEjercicios[i] = false
        }
        setContentView(binding.root)
        iniciarRecyclerViewPiernas(ejercicioPierna)
        iniciarRecyclerViewPecho(ejercicioPecho)
        iniciarRecyclerViewEspalda(ejercicioEspalda)
        iniciarRecyclerViewBrazo(ejercicioBrazo)
        iniciarRecyclerViewHombro(ejercicioHombros)
        iniciarRecyclerViewAbdominal(ejercicioAbdominal)
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
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)
            }
        }
        seleccionEjereciciosAdapter.notifyDataSetChanged()
    }
    private fun iniciarRecyclerViewPecho(ejercicios: List<Ejercicios>) {

        seleccionEjereciciosAdapter.addListaEjercicios(ejercicios)

        binding.pechoRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = seleccionEjereciciosAdapter
            seleccionEjereciciosAdapter.setOnItemClickListener { ejercicio ->
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)
            }
        }
        seleccionEjereciciosAdapter.notifyDataSetChanged()
    }
    private fun iniciarRecyclerViewEspalda(ejercicios: List<Ejercicios>) {

        seleccionEjereciciosAdapter.addListaEjercicios(ejercicios)

        binding.espaldaRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = seleccionEjereciciosAdapter
            seleccionEjereciciosAdapter.setOnItemClickListener { ejercicio ->
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)
            }
        }
        seleccionEjereciciosAdapter.notifyDataSetChanged()
    }
    private fun iniciarRecyclerViewAbdominal(ejercicios: List<Ejercicios>) {

        seleccionEjereciciosAdapter.addListaEjercicios(ejercicios)

        binding.abdominalesRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = seleccionEjereciciosAdapter
            seleccionEjereciciosAdapter.setOnItemClickListener { ejercicio ->
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)
            }
        }
        seleccionEjereciciosAdapter.notifyDataSetChanged()
    }
    private fun iniciarRecyclerViewBrazo(ejercicios: List<Ejercicios>) {

        seleccionEjereciciosAdapter.addListaEjercicios(ejercicios)

        binding.brazosRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = seleccionEjereciciosAdapter
            seleccionEjereciciosAdapter.setOnItemClickListener { ejercicio ->
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)

            }
        }
        seleccionEjereciciosAdapter.notifyDataSetChanged()
    }
    private fun iniciarRecyclerViewHombro(ejercicios: List<Ejercicios>) {

        seleccionEjereciciosAdapter.addListaEjercicios(ejercicios)

        binding.hombroRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = seleccionEjereciciosAdapter
            seleccionEjereciciosAdapter.setOnItemClickListener { ejercicio ->
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)

            }
        }
        seleccionEjereciciosAdapter.notifyDataSetChanged()
    }
}