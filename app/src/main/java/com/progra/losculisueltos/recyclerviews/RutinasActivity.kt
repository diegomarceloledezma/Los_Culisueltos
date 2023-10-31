package com.progra.losculisueltos

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.progra.losculisueltos.RutinaEjerciciosActivity.Companion.CLAVE_RUTINA
import com.progra.losculisueltos.adapter.RutinasAdapter
import com.progra.losculisueltos.databinding.ActivityRutinasBinding
import com.progra.losculisueltos.dataclases.EjercicioInfo
import com.progra.losculisueltos.dataclases.Ejercicios
import com.progra.losculisueltos.dataclases.Historial
import com.progra.losculisueltos.dataclases.Rutinas
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RutinasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRutinasBinding
    private val rutinasAdapter by lazy { RutinasAdapter() }
    private lateinit var preference: SharedPreferences
    lateinit var jsonMap: String
    var cambiosRealizados: Boolean = false
    var listHistorial: MutableList<Historial> = mutableListOf()
    val listRutinas = mutableListOf<Rutinas>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutinasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preference = PreferenceManager.getDefaultSharedPreferences(this)
        jsonMap = preference.getString(MenuActivity.CLAVE_HISTORIAL_LISTA, null)?: ""
        if (jsonMap != "") {
            listHistorial= Gson().fromJson(jsonMap, object : TypeToken<List<Historial>>() {}.type)
        }
        val editor = preference.edit()
        val historialNuevo: Historial = Historial(
                fecha = "",
                rutinas = Rutinas(
                    nombre="",
                    ejercicios = mutableMapOf<Int, Boolean>(),
                    infoEjercio = mutableListOf<EjercicioInfo>()
                )
                )
        val gson = Gson()
        val mapaSerializado = gson.toJson(historialNuevo)
        editor.putString(CLAVE_HISTORIAL, mapaSerializado)
        editor.apply()
        binding.addRutina.setOnClickListener {
            val rutina: Rutinas = Rutinas(
                nombre="",
                ejercicios = mutableMapOf<Int, Boolean>(),
                infoEjercio = mutableListOf<EjercicioInfo>()
            )
            val gson1 = Gson()
            val rutinaSerializado = gson1.toJson(rutina)
            editor.putString(CLAVE_RUTINA_L, rutinaSerializado)
            editor.apply()
            preference = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = preference.edit()
            val mapaEjerciciosNuevos: MutableMap<Int, Boolean> = mutableMapOf()
            val gson = Gson()
            val mapaSerializado = gson.toJson(mapaEjerciciosNuevos)
            editor.putString(RutinaEjerciciosActivity.CLAVE_EJERCICIOS, mapaSerializado)
            editor.apply()
            val intent = Intent(this@RutinasActivity, RutinaEjerciciosActivity::class.java)
            intent.putExtra(CLAVE_RUTINA, rutina)
            startActivity(intent)
        }
        iniciarRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        cargarYActualizarDatos()
    }
    private fun cargarYActualizarDatos() {
        cambiosRealizados = preference.getBoolean("cambiosRealizadosRutinas", false)?: false

        if (cambiosRealizados) {

            preference = PreferenceManager.getDefaultSharedPreferences(this)
            jsonMap = preference.getString(CLAVE_RUTINA_L, null)?: ""
            if (jsonMap != "") {
                val rutinaNuevo :Rutinas = Gson().fromJson(jsonMap, object : TypeToken<Rutinas>() {}.type)
                listRutinas.add(rutinaNuevo)
            }

            val editor = preference.edit()

            val gson1 = Gson()
            val listSerializado = gson1.toJson(listRutinas)
            editor.putString(MenuActivity.CLAVE_RUTINAS_LISTA, listSerializado)






            jsonMap = preference.getString(CLAVE_HISTORIAL, null)?: ""
            if (jsonMap != "") {
                val historialNuevo :Historial = Gson().fromJson(jsonMap, object : TypeToken<Historial>() {}.type)
                listHistorial.add(historialNuevo)
            }

            val gson = Gson()
            val mapaSerializado = gson.toJson(listHistorial)
            editor.putString(MenuActivity.CLAVE_HISTORIAL_LISTA, mapaSerializado)
            editor.putBoolean("cambiosRealizadosHistorial", true)
            editor.apply()
            Toast.makeText(this, "Se realizaron los cambios.", Toast.LENGTH_SHORT).show()
            iniciarRecyclerView()
            editor.putBoolean("cambiosRealizadosRutinas", false)
            editor.apply()
            iniciarRecyclerView()
        }
    }
    fun iniciarRecyclerView(){


        rutinasAdapter.addRutinas(listRutinas)
        binding.rutinasRecycler.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter=rutinasAdapter
            rutinasAdapter.setOnItemClickListener { rutinait ->

                val intent = Intent(this@RutinasActivity, RutinaEjerciciosActivity::class.java)
                intent.putExtra(CLAVE_RUTINA, rutinait)
                startActivity(intent)
            }
        }
        rutinasAdapter.notifyDataSetChanged()
    }
    companion object{
        val CLAVE_HISTORIAL = "clave_historial"
        val CLAVE_RUTINA_L = "clave_rutina_2"
    }
}