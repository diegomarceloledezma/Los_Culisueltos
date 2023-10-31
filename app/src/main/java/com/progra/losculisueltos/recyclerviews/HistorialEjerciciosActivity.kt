package com.progra.losculisueltos.recyclerviews

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.R
import com.progra.losculisueltos.adapter.HistorialEjerciciosAdapter
import com.progra.losculisueltos.databinding.ActivityHistorialEjerciciosBinding
import com.progra.losculisueltos.dataclases.Ejercicios
import com.progra.losculisueltos.dataclases.Historial

class HistorialEjerciciosActivity : AppCompatActivity() {
    lateinit var binding: ActivityHistorialEjerciciosBinding
    val context: Context = this
    private val historialEjercicioAdapter by lazy {HistorialEjerciciosAdapter()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorialEjerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val historial : Historial = intent.getSerializableExtra(CLAVE_HISTORIAL) as Historial

        iniciarRecyclerView()

    }

    fun iniciarRecyclerView(){
        val history = mutableListOf<Historial>()


        historialEjercicioAdapter.addHistorial2(history)
        binding.historialEjerciciosRecycler.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter=historialEjercicioAdapter
        }
        historialEjercicioAdapter.notifyDataSetChanged()
    }
    companion object{
        val CLAVE_HISTORIAL = "clave_historial"
    }

}