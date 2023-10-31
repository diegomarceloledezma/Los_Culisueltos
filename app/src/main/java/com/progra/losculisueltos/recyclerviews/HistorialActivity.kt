package com.progra.losculisueltos.recyclerviews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.adapter.HistorialAdapter
import com.progra.losculisueltos.databinding.ActivityHistorialBinding
import com.progra.losculisueltos.dataclases.EjercicioInfo
import com.progra.losculisueltos.dataclases.Historial
import com.progra.losculisueltos.dataclases.Rutinas
import com.progra.losculisueltos.recyclerviews.HistorialEjerciciosActivity.Companion.CLAVE_HISTORIAL

class HistorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistorialBinding
    private val historialAdapter by lazy {HistorialAdapter()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciarRecyclerView()
    }

    fun iniciarRecyclerView(){
        val historial = mutableListOf<Historial>()
        val historial1 = Historial(
            fecha = "27/08/2003",
            rutinas = Rutinas(
                nombre= "Pepe",
            ejercicios = mutableMapOf(),
                infoEjercio = mutableListOf()
            )
        )


        historial.add(historial1)

        historialAdapter.addHistorial(historial)
        binding.historialRecycler.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter=historialAdapter
            historialAdapter.setOnItemClickListener{ historiall1 ->
                val intent = Intent(this@HistorialActivity,HistorialEjerciciosActivity::class.java)
                intent.putExtra(CLAVE_HISTORIAL,historial1)
                startActivity(intent)
            }
        }
        historialAdapter.notifyDataSetChanged()
    }
}

