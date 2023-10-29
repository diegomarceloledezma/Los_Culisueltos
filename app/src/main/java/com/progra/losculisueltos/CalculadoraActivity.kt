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
import java.util.Date
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FieldValue
import com.progra.losculisueltos.dataclases.PesosInfo
import com.progra.losculisueltos.dataclases.Usuario
import java.util.Calendar
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
                val peso: Int = pesoText.toInt()
                val estatura: Int = estaturaText.toInt()
                var tmb: Double
                val calendar = Calendar.getInstance()
                val diaDelMes = calendar.get(Calendar.DAY_OF_MONTH)
                val diasEnElMes = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
                var diaDivididoPorDiasDelMes = diaDelMes.toDouble() / diasEnElMes
                //val nombreDelMes = obtenerNombreDelMes(calendar.get(Calendar.MONTH))

                if(genero==1){
                    tmb = (10.0 * peso) + (6.25*estatura) - (5.0 *edad) + 5.0
                }
                else{
                    tmb = (10.0 * peso) + (6.25*estatura) - (5.0*edad) + 161.0
                }

               /* val auth = Firebase.auth
                val user = auth.currentUser

                if (user != null) {
                    val nuevaDiaDivididoPorDiasDelMes = diaDivididoPorDiasDelMes

                    val db = FirebaseFirestore.getInstance()
                    val userDocumentRef = db.collection("usuarios").document(user.uid)

                    userDocumentRef.get().addOnSuccessListener { documentSnapshot ->
                        if (documentSnapshot.exists()) {
                            val usuario = documentSnapshot.toObject(Usuario::class.java)
                            val nuevaListaPesos = actualizarListaPesos(usuario?.pesos, peso, nuevaDiaDivididoPorDiasDelMes, nombreDelMes, 15)

                            userDocumentRef.update("pesos", nuevaListaPesos)
                        }
                    }
                }*/

                tmb *= mul[opcion]
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

    /*private fun actualizarListaPesos(
        pesos: List<PesosInfo>?,
        nuevoPeso: Int,
        nuevaFecha: Double,
        nombreDelMes: String,
        maxSize: Int
    ): List<PesosInfo> {
        val listaPesos = mutableListOf<PesosInfo>()

        listaPesos.add(PesosInfo(nombreDelMes, nuevaFecha, nuevoPeso))

        pesos?.forEach {
            listaPesos.add(it)
        }
        if (listaPesos.size > maxSize) {
            listaPesos.removeAt(0)
        }

        return listaPesos
    }
    private fun obtenerNombreDelMes(mes: Int): String {
        val meses = arrayOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")
        return meses[mes]
    }*/
}