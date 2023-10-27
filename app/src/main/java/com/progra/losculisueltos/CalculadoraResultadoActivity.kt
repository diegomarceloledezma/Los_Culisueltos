package com.progra.losculisueltos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.progra.losculisueltos.databinding.ActivityCalculadoraResultadoBinding


class CalculadoraResultadoActivity : AppCompatActivity() {

    lateinit var binding: ActivityCalculadoraResultadoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculadoraResultadoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val resultado : Int= intent.getIntExtra(CLAVE_INT,0)

        binding.perderPeso.text="Consumir entre ${resultado-700} - ${resultado-500} para poder bajar de peso y actividad fisica constante"
        binding.ganarMusculo1.text="Consumir entre ${resultado+300} - ${resultado+400} para poder bajar de peso y actividad fisica constante"
        binding.ganarFuerza.text="Consumir entre ${resultado+100} - ${resultado+200} para poder bajar de peso y actividad fisica constante"
        binding.mantenerse.text="Consumir entre ${resultado-100} - ${resultado} para poder mantener tu peso y actividad fisica constante"
    }
    companion object{
        val CLAVE_INT = "clave_int"
    }
}