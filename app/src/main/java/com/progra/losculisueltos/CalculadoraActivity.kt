package com.progra.losculisueltos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.content.Context
import androidx.core.content.ContextCompat
import com.progra.losculisueltos.CalculadoraResultadoActivity.Companion.CLAVE_INT
import com.progra.losculisueltos.databinding.ActivityCalculadoraBinding
class CalculadoraActivity : AppCompatActivity() {
    lateinit var binding: ActivityCalculadoraBinding
    val context: Context = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCalculadoraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var genero = 0
        binding.masculino1.setOnClickListener {
            genero = 1
            binding.masculino1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.cyan)
            binding.femenino1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.white)
        }
        binding.femenino1.setOnClickListener {
            genero = 2
            binding.femenino1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.cyan)
            binding.masculino1.backgroundTintList = ContextCompat.getColorStateList(this, R.color.white)
        }

        binding.calcularCal.setOnClickListener {
            val edadText = binding.edadCal.text.toString()
            val pesoText = binding.pesoCal.text.toString()
            val estaturaText = binding.estaturaCal.text.toString()
            if (genero!=0 && edadText.isNotEmpty() && pesoText.isNotEmpty() && estaturaText.isNotEmpty()) {
                val edad: Int = edadText.toInt()
                val peso: Int = edadText.toInt()
                val estatura: Int = estaturaText.toInt()
                val tmb: Double
                if(genero==1){
                    tmb =  66 + (13.7*peso) + (5*estatura) - (6.8 * edad)
                }
                else{
                    tmb = 655 + (9.6 * peso) + (1.8*estatura) - (4.7* edad)
                }

                val numeroInt: Int = tmb.toInt()
                val intent: Intent = Intent(context, CalculadoraResultadoActivity::class.java)
                intent.putExtra(CLAVE_INT,numeroInt)
                startActivity(intent)

            } else {
                val mensaje = "LLena todos los espacios"
                val duracion = Toast.LENGTH_SHORT
                val toast = Toast.makeText(this, mensaje, duracion)
                toast.show()
            }

        }
    }
}