package com.progra.losculisueltos.recyclerviews

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.PerfilActivity
import com.progra.losculisueltos.R
import com.progra.losculisueltos.adapter.HistorialEjerciciosAdapter
import com.progra.losculisueltos.databinding.ActivityHistorialEjerciciosBinding
import com.progra.losculisueltos.dataclases.EjercicioInfo
import com.progra.losculisueltos.dataclases.Ejercicios
import com.progra.losculisueltos.dataclases.Historial

class HistorialEjerciciosActivity : AppCompatActivity() {
    lateinit var binding: ActivityHistorialEjerciciosBinding
    val context: Context = this
    private val historialEjercicioAdapter by lazy {HistorialEjerciciosAdapter()}
    lateinit var historial: Historial
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorialEjerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        historial= intent.getSerializableExtra(CLAVE_HISTORIAL_AC) as Historial
        binding.historialEjerciciosFecha.text = historial.fecha
        iniciarRecyclerView()

    }

    fun iniciarRecyclerView(){
        val history = mutableListOf<EjercicioInfo>()
        if(historial.rutinas != null){
            val rutinas = historial.rutinas // Almacena el valor en una variable temporal

            for (i in historial.rutinas?.infoEjercio ?: emptyList()) {
                history.add(i)
            }
        }
        binding.buttonMenu.setOnClickListener {
            finish()
        }
        binding.buttonUser.setOnClickListener {
            val intent: Intent = Intent(context, PerfilActivity::class.java)
            startActivity(intent)
        }

        historialEjercicioAdapter.addHistorial2(history)
        binding.historialEjerciciosRecycler.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter=historialEjercicioAdapter
            historialEjercicioAdapter.setOnItemClickListener {ejercicioInfo1 ->

                val imagen2: Int = ejercicioInfo1.ejercicio.imagenMusculo

                binding.imageView.setImageResource(imagen2)

            }
        }
        historialEjercicioAdapter.notifyDataSetChanged()
    }
    companion object{
        val CLAVE_HISTORIAL_AC = "clave_historial_a"
    }

}