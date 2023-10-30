package com.progra.losculisueltos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.adapter.ComidaAdapter
import com.progra.losculisueltos.databinding.ActivityComidaIndividualBinding
import com.progra.losculisueltos.dataclases.Comidas

class ComidaIndividualActivity : AppCompatActivity() {

    private lateinit var binding: ActivityComidaIndividualBinding
    private val comidaAdapter by lazy { ComidaAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComidaIndividualBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciarRecycleViewDesayuno()
    }

    fun iniciarRecycleViewDesayuno(){
        val comidasDesayuno = mutableListOf<Comidas>()

        val comida1 = Comidas(
            nombre = "Batido de espinacas y platano",
            calorias = 175,
            ingredientes = listOf("1 taza de espinacas frescas", "1 plátano maduro", "1/2 taza de leche desnatada (u otra alternativa baja en calorías, como leche de almendra sin azúcar)", "1 cucharadita de miel (opcional)", "Hielo al gusto"),
            preparacion = "Coloca las espinacas, el plátano, la leche (o alternativa de leche baja en calorías), la miel (si lo deseas) y el hielo en una licuadora. Mezcla hasta obtener una consistencia suave y homogénea.",
            imagenC = R.drawable.batido_espinacas_platano
        )
        comidasDesayuno.add(comida1)
        comidaAdapter.addComidas(comidasDesayuno)
        binding.recycleViewComidas.apply {
            layoutManager = LinearLayoutManager(
                context, LinearLayoutManager.VERTICAL,false
            )
            adapter = comidaAdapter
        }
        comidaAdapter.notifyDataSetChanged()
    }
}