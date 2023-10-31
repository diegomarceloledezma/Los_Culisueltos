package com.progra.losculisueltos

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import com.google.gson.reflect.TypeToken
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
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
    val adapterPiernas = SeleccionEjereciciosAdapter()
    val adapterPecho = SeleccionEjereciciosAdapter()
    val adapterEspalda = SeleccionEjereciciosAdapter()
    val adapterAbdominales = SeleccionEjereciciosAdapter()
    val adapterBrazos = SeleccionEjereciciosAdapter()
    val adapterHombros = SeleccionEjereciciosAdapter()
    var mapaEjercicios: MutableMap<Int, Boolean> = mutableMapOf()
    private lateinit var preference: SharedPreferences
    lateinit var jsonMap: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySeleccionEjerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preference = PreferenceManager.getDefaultSharedPreferences(this)
        jsonMap = preference.getString(RutinaEjerciciosActivity.CLAVE_EJERCICIOS, null)?: ""
        if (jsonMap != "") {
            mapaEjercicios= Gson().fromJson(jsonMap, object : TypeToken<Map<Int, Boolean>>() {}.type)
        }
        iniciarRecyclerViewPiernas(ejercicioPierna)
        iniciarRecyclerViewPecho(ejercicioPecho)
        iniciarRecyclerViewEspalda(ejercicioEspalda)
        iniciarRecyclerViewBrazo(ejercicioBrazo)
        iniciarRecyclerViewHombro(ejercicioHombros)
        iniciarRecyclerViewAbdominal(ejercicioAbdominal)

        binding.guardarEjercicios.setOnClickListener {
            val gson = Gson()
            val jsonMap = gson.toJson(mapaEjercicios)

            val editor = preference.edit()
            editor.putString(RutinaEjerciciosActivity.CLAVE_EJERCICIOS, jsonMap)
            editor.putBoolean("cambiosRealizados", true)
            editor.apply()

            finish()

        }


    }

    private fun iniciarRecyclerViewPiernas(ejercicios: List<Ejercicios>) {

        adapterPiernas.addListaEjercicios(ejercicios)

        binding.piernasRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = adapterPiernas
            adapterPiernas.setOnItemClickListener { ejercicio ->
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)
            }
        }
        adapterPiernas.notifyDataSetChanged()
    }
    private fun iniciarRecyclerViewPecho(ejercicios: List<Ejercicios>) {

        adapterPecho.addListaEjercicios(ejercicios)

        binding.pechoRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = adapterPecho
            adapterPecho.setOnItemClickListener { ejercicio ->
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)
            }
        }
        adapterPecho.notifyDataSetChanged()
    }
    private fun iniciarRecyclerViewEspalda(ejercicios: List<Ejercicios>) {

        adapterEspalda.addListaEjercicios(ejercicios)

        binding.espaldaRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = adapterEspalda
            adapterEspalda.setOnItemClickListener { ejercicio ->
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)
            }
        }
        adapterEspalda.notifyDataSetChanged()
    }
    private fun iniciarRecyclerViewAbdominal(ejercicios: List<Ejercicios>) {

        adapterAbdominales.addListaEjercicios(ejercicios)

        binding.abdominalesRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = adapterAbdominales
            adapterAbdominales.setOnItemClickListener { ejercicio ->
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)
            }
        }
        adapterAbdominales.notifyDataSetChanged()
    }
    private fun iniciarRecyclerViewBrazo(ejercicios: List<Ejercicios>) {

        adapterBrazos.addListaEjercicios(ejercicios)

        binding.brazosRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = adapterBrazos
            adapterBrazos.setOnItemClickListener { ejercicio ->
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)

            }
        }
        adapterBrazos.notifyDataSetChanged()
    }
    private fun iniciarRecyclerViewHombro(ejercicios: List<Ejercicios>) {

        adapterHombros.addListaEjercicios(ejercicios)

        binding.hombroRecycler.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = adapterHombros
            adapterHombros.setOnItemClickListener { ejercicio ->
                mapaEjercicios[ejercicio.id] = (mapaEjercicios[ejercicio.id] ?: false).xor(true)

            }
        }
        adapterHombros.notifyDataSetChanged()
    }
}