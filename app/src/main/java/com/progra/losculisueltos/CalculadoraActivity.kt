package com.progra.losculisueltos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.widget.ArrayAdapter;
import android.os.Bundle
import android.widget.Toast
import android.content.Context
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
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
        var opcion = -1
        val mul = listOf(1.2,1.375,1.55,1.725,1.9)
        val item = listOf("Sedentario","Poco Activo", "Activo Moderado", "Activo","Muy Activo")
        val autoComplete: AutoCompleteTextView = binding.actividadFisica

        val adapter = ArrayAdapter(this, R.layout.dropdown_item,item)

        autoComplete.setAdapter(adapter)
        autoComplete.onItemClickListener = AdapterView.OnItemClickListener{
                adapterView, view, i, l ->
            val itemSelected = adapterView.getItemAtPosition(i)
            binding.opciones.hint = itemSelected.toString()
            opcion=i
        }
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
            if (genero!=0 && edadText.isNotEmpty() && pesoText.isNotEmpty() && estaturaText.isNotEmpty() && opcion!=-1) {
                val edad: Int = edadText.toInt()
                val peso: Int = edadText.toInt()
                val estatura: Int = estaturaText.toInt()
                var tmb: Double

                if(genero==1){
                    tmb =  66.5 + (13.75 * peso) + (5.003*estatura) - (6.75 *edad)
                }
                else{
                    tmb = 655.1 + (9.563 * peso) + (1.850*estatura) - (4.676*edad)
                }
                tmb = tmb * mul[opcion]
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