package com.progra.losculisueltos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.adapter.ExplicacionEjercicioAdapter
import com.progra.losculisueltos.databinding.ActivityRecyclerViewExplicacionEjercicioBinding
import com.progra.losculisueltos.dataclases.Ejercicios

class RecyclerViewExplicacionEjercicioActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRecyclerViewExplicacionEjercicioBinding
    private val explicacionEjercicioAdapter by lazy { ExplicacionEjercicioAdapter() }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerViewExplicacionEjercicioBinding.inflate(layoutInflater)
        setContentView(binding.root)
        iniciarRecyclerView()
    }

    private fun iniciarRecyclerView() {
        val ejercicios = mutableListOf<Ejercicios>()
        val abdominal = Ejercicios(
            nombre = "Asciende de Piernas Extendidas en Tabla Vertical",
            imagenE = R.drawable.asciende_de_piernas_extendidas_en_tabla_vertical,
            explicacion = "En la máquina, nos elevamos con los antebrazos inclinados 90 grados con los hombros, debemos de mantener un equilibrio al momento de solo mover las piernas hacia nuestro estómago.  Todo este proceso con las piernas extendidas.",
            parteDelCuerpo = "Abdominal",
            imagenMusculo = R.drawable.comida,
            id = "a1"
        )

        ejercicios.add(abdominal)


        explicacionEjercicioAdapter.addEjercicios(ejercicios)

        binding.recyclerMaquina.apply {
            layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            adapter = explicacionEjercicioAdapter
        }
        explicacionEjercicioAdapter.notifyDataSetChanged()
    }

}