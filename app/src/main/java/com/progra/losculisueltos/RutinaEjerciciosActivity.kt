package com.progra.losculisueltos

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.databinding.ActivityRutinaEjerciciosBinding
import com.progra.losculisueltos.dataclases.Ejercicios
import com.progra.losculisueltos.dataclases.Rutinas

class RutinaEjerciciosActivity : AppCompatActivity() {
    lateinit var binding: ActivityRutinaEjerciciosBinding
    val context: Context = this
    private val ejercicioAdapter by lazy {RutinasEjerciciosAdapter()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutinaEjerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rutina : Rutinas = intent.getSerializableExtra(CLAVE_RUTINA) as Rutinas

        binding.rutinaNombre.text = Editable.Factory.getInstance().newEditable(rutina.nombre)

        iniciarRecyclerView()


    }
    fun iniciarRecyclerView(){
        val ejercicios = mutableListOf<Ejercicios>()


        ejercicioAdapter.addRutinas(ejercicios)
        binding.rutinasRecycler.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter=ejercicioAdapter
        }
        ejercicioAdapter.notifyDataSetChanged()
    }
    companion object{
        val CLAVE_RUTINA = "clave_rutina"
    }
}

