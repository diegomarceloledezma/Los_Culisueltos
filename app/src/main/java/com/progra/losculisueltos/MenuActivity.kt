package com.progra.losculisueltos

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import com.google.gson.Gson
import com.progra.losculisueltos.databinding.ActivityMenuBinding
import com.progra.losculisueltos.dataclases.Historial
import com.progra.losculisueltos.dataclases.Rutinas
import com.progra.losculisueltos.recyclerviews.HistorialActivity


class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding

    private lateinit var preference: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.nutricion.setOnClickListener {
            val intent = Intent(this, ComidaActivity::class.java)
            startActivity(intent)
        }
        binding.maquinas.setOnClickListener {
            val intent = Intent(this, MenuEjerciciosActivity::class.java)
            startActivity(intent)
        }
        binding.rutinas.setOnClickListener {
            val intent = Intent(this,RutinasActivity::class.java)
            startActivity(intent)
        }
        binding.historial.setOnClickListener {
            val intent = Intent(this,HistorialActivity::class.java)
            startActivity(intent)
        }
        binding.acercaDe.setOnClickListener {
            val intent = Intent(this,AcercaDeActivity::class.java)
            startActivity(intent)
        }
        binding.buttonUser.setOnClickListener {
            val intent: Intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }
    }
    companion object{
        val CLAVE_HISTORIAL_LISTA= "lista_historial"
        val CLAVE_RUTINAS_LISTA="lista_rutinas"
    }
}