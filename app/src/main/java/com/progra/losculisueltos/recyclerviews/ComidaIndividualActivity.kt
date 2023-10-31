package com.progra.losculisueltos.recyclerviews

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.adapter.ComidaAdapter
import com.progra.losculisueltos.databinding.ActivityComidaIndividualBinding
import com.progra.losculisueltos.dataclases.Comidas

class ComidaIndividualActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComidaIndividualBinding
    val context: Context = this
    private val comidaAdapter by lazy { ComidaAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComidaIndividualBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val comida : Comidas = intent.getSerializableExtra(CLAVE_COMIDA) as Comidas
        iniciarRecycleViewDesayuno(comida)
    }

    fun iniciarRecycleViewDesayuno(comida: Comidas) {
        val comidasDesayuno = mutableListOf<Comidas>()

        comidasDesayuno.add(comida)
        comidaAdapter.addComidas(comidasDesayuno)
        binding.recycleViewComidas.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)
            adapter = comidaAdapter
        }
        comidaAdapter.notifyDataSetChanged()
    }

    companion object{
        val CLAVE_COMIDA = "clave_comida"
    }
}