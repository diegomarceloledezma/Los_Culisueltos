package com.progra.losculisueltos

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import com.google.gson.Gson
import android.text.Editable
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.reflect.TypeToken
import com.progra.losculisueltos.RutinasActivity.Companion.CLAVE_HISTORIAL
import com.progra.losculisueltos.RutinasActivity.Companion.CLAVE_RUTINA_L
import com.progra.losculisueltos.databinding.ActivityRutinaEjerciciosBinding
import com.progra.losculisueltos.dataclases.EjercicioInfo
import com.progra.losculisueltos.dataclases.Ejercicios
import com.progra.losculisueltos.dataclases.Historial
import com.progra.losculisueltos.dataclases.Rutinas
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class RutinaEjerciciosActivity : AppCompatActivity() {
    lateinit var binding: ActivityRutinaEjerciciosBinding
    val context: Context = this
    val ejerciciosBase = ListaDeEjerciciosProvider()
    val ejercicioPierna = ejerciciosBase.listaPiernas
    val ejercicioPecho = ejerciciosBase.listaPecho
    val ejercicioEspalda = ejerciciosBase.listaEspalda
    val ejercicioBrazo = ejerciciosBase.listaBrazos
    val ejercicioHombros = ejerciciosBase.listaHombros
    val ejercicioAbdominal = ejerciciosBase.listaAbdominales

    private val ejercicioAdapter by lazy { RutinasEjerciciosAdapter() }
    private lateinit var preference: SharedPreferences
    lateinit var jsonMap: String
    var cambiosRealizados: Boolean = false
    var mapaEjercicios: MutableMap<Int, Boolean> = mutableMapOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRutinaEjerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val rutina: Rutinas = intent.getSerializableExtra(CLAVE_RUTINA) as Rutinas

        binding.rutinaNombre.text = Editable.Factory.getInstance().newEditable(rutina.nombre)
        binding.addRutina.setOnClickListener {
            val intent = Intent(this, SeleccionEjerciciosActivity::class.java)
            startActivity(intent)
        }
        binding.guardarRutina.setOnClickListener {
            if(binding.rutinaNombre.text.toString().isEmpty())
                Toast.makeText(this, "LLena El Nombre", Toast.LENGTH_SHORT).show()
            else if(ejercicioAdapter.itemCount==0)
                Toast.makeText(this, "Selecciona al menos un ejercicio", Toast.LENGTH_SHORT).show()
            else{
                var flag: Boolean = true
                val listEjercicioInfo: MutableList<EjercicioInfo> = mutableListOf<EjercicioInfo>()
                for (i in 0 until ejercicioAdapter.itemCount) {
                    val viewHolder = binding.rutinasRecycler.findViewHolderForAdapterPosition(i) as RutinasEjerciciosAdapter.RutinasEjerciciosAdapterViewHolder?
                    if (viewHolder != null) {
                        val ejercicio1 = ejercicioAdapter.getEjercicioAtPosition(i)
                        val repText = viewHolder.repEjercicio.text.toString()
                        val serText = viewHolder.serEjercicio.text.toString()
                        val pesoText = viewHolder.pesoEjercicio.text.toString()

                        if (repText.isNotEmpty() && serText.isNotEmpty() && pesoText.isNotEmpty()) {
                            val repeticiones = repText.toInt()
                            val series1 = serText.toInt()
                            val peso1 = pesoText.toInt()

                            val ejercicioInfo = EjercicioInfo(
                                ejercicio = ejercicio1,
                                repes = repeticiones,
                                series = series1,
                                peso = peso1
                            )
                            listEjercicioInfo.add(ejercicioInfo)
                        }
                        else{
                            flag = false
                        }
                    }
                }
                if(flag){
                    rutina.nombre = binding.rutinaNombre.text.toString()
                    var ejercicios: MutableList<Ejercicios> = mutableListOf()
                    preference = PreferenceManager.getDefaultSharedPreferences(this)
                    jsonMap = preference.getString(CLAVE_EJERCICIOS, null)?: ""
                    if (jsonMap != "") {
                        mapaEjercicios= Gson().fromJson(jsonMap, object : TypeToken<Map<Int, Boolean>>() {}.type)
                    }
                    rutina.ejercicios = mapaEjercicios
                    rutina.infoEjercio = listEjercicioInfo





                    preference = PreferenceManager.getDefaultSharedPreferences(this)
                    val editor = preference.edit()
                    val gson1 = Gson()
                    val jsonRut = gson1.toJson(rutina)
                    editor.putString(CLAVE_RUTINA_L, jsonRut)
                    editor.putBoolean("cambiosRealizadosRutinas", true)
                    editor.apply()

                    val calendar = Calendar.getInstance()
                    val dateFormat = SimpleDateFormat("dd 'de' MMMM 'de' yyyy", Locale.getDefault())
                    val historialNuevo: Historial = Historial(
                        fecha = "$dateFormat.format(calendar.time)",
                        rutinas = rutina
                    )
                    val gson = Gson()
                    val jsonMap = gson.toJson(historialNuevo)

                    editor.putString(CLAVE_HISTORIAL, jsonMap)
                    editor.apply()


                    finish()

                }
                else{
                    Toast.makeText(this, "Completa Bien Los Espacios", Toast.LENGTH_SHORT).show()
                }
            }

        }
        iniciarRecyclerView()
    }

    override fun onResume() {
        super.onResume()

        cargarYActualizarDatos()
    }
    private fun cargarYActualizarDatos() {
        cambiosRealizados = preference.getBoolean("cambiosRealizados", false)?: false

        if (cambiosRealizados) {
            Toast.makeText(this, "Se realizaron cambios en los ejercicios.", Toast.LENGTH_SHORT).show()
            iniciarRecyclerView()
            val editor = preference.edit()
            editor.putBoolean("cambiosRealizados", false)
            editor.apply()
        }
    }
    fun iniciarRecyclerView() {
        var ejercicios: MutableList<Ejercicios> = mutableListOf()
        preference = PreferenceManager.getDefaultSharedPreferences(this)
        jsonMap = preference.getString(CLAVE_EJERCICIOS, null)?: ""
        if (jsonMap != "") {
            mapaEjercicios= Gson().fromJson(jsonMap, object : TypeToken<Map<Int, Boolean>>() {}.type)
        }
        for(i in ejercicioPierna){
            if(mapaEjercicios[i.id]?:false){
                ejercicios.add(i)
            }
        }
        for(i in ejercicioPecho){
            if(mapaEjercicios[i.id]?:false){
                ejercicios.add(i)
            }
        }
        for(i in ejercicioHombros){
            if(mapaEjercicios[i.id]?:false){
                ejercicios.add(i)
            }
        }
        for(i in ejercicioEspalda){
            if(mapaEjercicios[i.id]?:false){
                ejercicios.add(i)
            }
        }
        for(i in ejercicioBrazo){
            if(mapaEjercicios[i.id]?:false){
                ejercicios.add(i)
            }
        }
        for(i in ejercicioAbdominal){
            if(mapaEjercicios[i.id]?:false){
                ejercicios.add(i)
            }
        }
        ejercicioAdapter.addRutinas(ejercicios)
        binding.rutinasRecycler.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = ejercicioAdapter
        }
        ejercicioAdapter.notifyDataSetChanged()
    }

    companion object {
        val CLAVE_RUTINA = "clave_rutina"
        val CLAVE_EJERCICIOS = "mapaEjerciciosKey"
    }
}
