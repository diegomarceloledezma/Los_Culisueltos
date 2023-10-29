package com.progra.losculisueltos


import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.Toast
import com.progra.losculisueltos.databinding.ActivityPerfilBinding
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.ValueFormatter
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.firestore.FieldValue
import com.progra.losculisueltos.dataclases.Usuario


class PerfilActivity : AppCompatActivity() {
    lateinit var binding: ActivityPerfilBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val description = Description()
        description.text = "Peso vs Mes"
        binding.graficaUser.description = description

        val entries = ArrayList<Entry>()
        entries.add(Entry(1f, 70f))
        entries.add(Entry(2f, 72f))
        entries.add(Entry(3f, 75f))
        val dataSet = LineDataSet(entries, "Peso")
        dataSet.color = resources.getColor(R.color.cian_oscuro) // Color de la línea
        dataSet.valueTextColor = resources.getColor(R.color.black) // Color de los valores
        val customMonthLabels = arrayOf("Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre")

        val xAxis = binding.graficaUser.xAxis
        xAxis.valueFormatter = object : ValueFormatter() {
            override fun getFormattedValue(value: Float): String {
                val index = value.toInt()
                if (index >= 0 && index < customMonthLabels.size) {
                    return customMonthLabels[index]
                }
                return ""
            }
        }
        xAxis.setGranularity(1f)

        val lineData = LineData(dataSet)
        binding.graficaUser.setBackgroundColor(Color.WHITE)
        binding.graficaUser.data = lineData
        binding.graficaUser.invalidate() // Actualiza el gráfico
        binding.userBoton.setOnClickListener {
            if (binding.userText.visibility == View.VISIBLE) {
                val text = binding.userText.text.toString()
                binding.userText.visibility = View.GONE
                binding.userEdit.visibility = View.VISIBLE
                binding.userEdit.text = Editable.Factory.getInstance().newEditable(text)
                binding.userEdit.isEnabled = true
                binding.userEdit.requestFocus()
                val drawable = resources.getDrawable(R.drawable.confirmar_icon)
                binding.userBoton.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
            } else {
                binding.userText.visibility = View.VISIBLE
                binding.userEdit.visibility = View.GONE
                binding.userText.text = binding.userEdit.text.toString()

                val drawable = resources.getDrawable(R.drawable.edit_lapiz)
                binding.userBoton.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
                binding.userEdit.isEnabled = false
            }
        }

        binding.nombreBoton.setOnClickListener {
            if (binding.nombreText.visibility == View.VISIBLE) {
                val text = binding.nombreText.text.toString()
                binding.nombreText.visibility = View.GONE
                binding.nombreEdit.visibility = View.VISIBLE
                binding.nombreEdit.text = Editable.Factory.getInstance().newEditable(text)
                binding.nombreEdit.isEnabled = true
                val drawable = resources.getDrawable(R.drawable.confirmar_icon)
                binding.nombreBoton.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
                binding.nombreEdit.requestFocus()
            } else {
                binding.nombreText.visibility = View.VISIBLE
                binding.nombreEdit.visibility = View.GONE
                binding.nombreText.text = binding.nombreEdit.text.toString()
                val drawable = resources.getDrawable(R.drawable.edit_lapiz)
                binding.nombreBoton.setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null)
                binding.nombreEdit.isEnabled = false
            }
        }

    }


}