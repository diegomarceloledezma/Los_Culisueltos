package com.progra.losculisueltos.recyclerviews

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.progra.losculisueltos.MenuActivity
import com.progra.losculisueltos.PerfilActivity
import com.progra.losculisueltos.RutinaEjerciciosActivity
import com.progra.losculisueltos.adapter.HistorialAdapter
import com.progra.losculisueltos.databinding.ActivityHistorialBinding
import com.progra.losculisueltos.dataclases.EjercicioInfo
import com.progra.losculisueltos.dataclases.Historial
import com.progra.losculisueltos.dataclases.Rutinas
import com.progra.losculisueltos.recyclerviews.HistorialEjerciciosActivity.Companion.CLAVE_HISTORIAL_AC

class HistorialActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHistorialBinding
    private val historialAdapter by lazy {HistorialAdapter()}

    private lateinit var preference: SharedPreferences
    lateinit var jsonMap: String
    var historial: MutableList<Historial> = mutableListOf<Historial>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHistorialBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonMenu.setOnClickListener {
            finish()
        }
        binding.buttonUser.setOnClickListener {
            val intent: Intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }

        preference = PreferenceManager.getDefaultSharedPreferences(this)
        jsonMap = preference.getString(MenuActivity.CLAVE_HISTORIAL_LISTA, null)?: ""
        if (jsonMap != "") {
            historial= Gson().fromJson(jsonMap, object : TypeToken<MutableList<Historial>>() {}.type)

        }
        iniciarRecyclerView()
    }

    fun iniciarRecyclerView(){
        historialAdapter.addHistorial(historial)
        binding.historialRecycler.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter=historialAdapter
            historialAdapter.setOnItemClickListener{ historiall1 ->
                val intent = Intent(this@HistorialActivity,HistorialEjerciciosActivity::class.java)
                intent.putExtra(CLAVE_HISTORIAL_AC,historiall1)
                startActivity(intent)
            }
        }
        historialAdapter.notifyDataSetChanged()
    }
}

