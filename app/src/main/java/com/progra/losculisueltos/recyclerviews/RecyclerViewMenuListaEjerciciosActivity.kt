package com.progra.losculisueltos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.MenuEjerciciosActivity.Companion.CLAVE_LISTA_EJERCICIOS
import com.progra.losculisueltos.adapter.MenuListaEjerciciosAdapter
import com.progra.losculisueltos.databinding.ActivityRecyclerViewMenuListaEjerciciosBinding
import com.progra.losculisueltos.dataclases.Ejercicios
import com.progra.losculisueltos.recyclerviews.RecyclerViewExplicacionEjercicioActivity

class RecyclerViewMenuListaEjerciciosActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewMenuListaEjerciciosBinding
    private val menuListaEjerciciosAdapter by lazy { MenuListaEjerciciosAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewMenuListaEjerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val ejercicios: List<Ejercicios> = intent.getSerializableExtra(CLAVE_LISTA_EJERCICIOS) as List<Ejercicios>
        binding.buttonMenu.setOnClickListener {
            finish()
        }
        binding.buttonUser.setOnClickListener {
            val intent: Intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }
        iniciarRecyclerView(ejercicios)

    }
    private fun iniciarRecyclerView(ejercicios: List<Ejercicios>) {

        menuListaEjerciciosAdapter.addListaEjercicios(ejercicios)

        binding.listaEjercicios.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = menuListaEjerciciosAdapter
            menuListaEjerciciosAdapter.setOnItemClickListener { ejercicio ->
                val intent = Intent(this@RecyclerViewMenuListaEjerciciosActivity, RecyclerViewExplicacionEjercicioActivity::class.java)
                intent.putExtra(RecyclerViewExplicacionEjercicioActivity.CLAVE_EJERCICIO, ejercicio)
                startActivity(intent)
            }
        }
        menuListaEjerciciosAdapter.notifyDataSetChanged()
    }

}