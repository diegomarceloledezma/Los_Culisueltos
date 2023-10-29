package com.progra.losculisueltos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.adapter.ExplicacionEjercicioAdapter
import com.progra.losculisueltos.databinding.ActivityRecyclerViewExplicacionEjercicioBinding
import com.progra.losculisueltos.dataclases.Ejercicios

class RecyclerViewExplicacionEjercicioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewExplicacionEjercicioBinding
    private val explicacionEjercicioAdapter by lazy { ExplicacionEjercicioAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewExplicacionEjercicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ejercicios : Ejercicios = intent.getSerializableExtra(CLAVE_EJERCICIO) as Ejercicios


        iniciarRecyclerView(ejercicios)
    }

    private fun iniciarRecyclerView(ejercicios1: Ejercicios) {
        val ejercicios = mutableListOf<Ejercicios>()
        ejercicios.add(ejercicios1)



        explicacionEjercicioAdapter.addEjercicios(ejercicios)

        binding.recyclerMaquina.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = explicacionEjercicioAdapter
        }
        explicacionEjercicioAdapter.notifyDataSetChanged()
    }
    companion object{
        val CLAVE_EJERCICIO = "clave_ejercicio"
    }

}