package com.progra.losculisueltos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.RutinaEjerciciosActivity.Companion.CLAVE_RUTINA
import com.progra.losculisueltos.databinding.ActivityRutinasBinding
import com.progra.losculisueltos.dataclases.EjercicioInfo
import com.progra.losculisueltos.dataclases.Ejercicios
import com.progra.losculisueltos.dataclases.Rutinas

class RutinasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRutinasBinding
    private val rutinasAdapter by lazy {RutinasAdapter()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutinasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciarRecyclerView()
    }
    fun iniciarRecyclerView(){
        val rutinas = mutableListOf<Rutinas>()
        val rutina = Rutinas(
            id = 1,
            nombre = "PECHO Y TRICEPS",
            ejercicios = mutableListOf<EjercicioInfo>()
        )

        val rutina1 = Rutinas(
            id = 2,
            nombre = "PIERNAS Y HOMBROS",
            ejercicios = mutableListOf<EjercicioInfo>()
        )
        val rutina2 = Rutinas(
            id = 3,
            nombre = "ESPALDA Y BICEPS",
            ejercicios = mutableListOf<EjercicioInfo>()
        )

        rutinas.add(rutina)
        rutinas.add(rutina1)
        rutinas.add(rutina2)

        rutinasAdapter.addRutinas(rutinas)
        binding.rutinasRecycler.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter=rutinasAdapter
            rutinasAdapter.setOnItemClickListener { rutina ->
                val intent = Intent(this@RutinasActivity, RutinaEjerciciosActivity::class.java)
                intent.putExtra(CLAVE_RUTINA, rutina)
                startActivity(intent)
            }
        }
        rutinasAdapter.notifyDataSetChanged()
    }
}