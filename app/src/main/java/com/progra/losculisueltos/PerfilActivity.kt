package com.progra.losculisueltos


import android.app.ActivityManager
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.view.View
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
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
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.progra.losculisueltos.dataclases.Historial
import com.progra.losculisueltos.dataclases.Rutinas
import com.progra.losculisueltos.dataclases.Usuario


class PerfilActivity : AppCompatActivity() {
    lateinit var binding: ActivityPerfilBinding
    private lateinit var preference: SharedPreferences
    lateinit var jsonMap: String
    lateinit var usuarioDatos: Usuario

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val auth = FirebaseAuth.getInstance()
        val user = auth.currentUser
        val uid = user?.uid
        if(uid != null){
            preference = PreferenceManager.getDefaultSharedPreferences(this)
            jsonMap = preference.getString(uid, null)?: ""

            if (jsonMap != "") {
                usuarioDatos= Gson().fromJson(jsonMap, object : TypeToken<Usuario>() {}.type)
            }
        }
        else{
            auth.signOut()
        }

        if(usuarioDatos!=null){
            binding.nombreText.text = usuarioDatos.nombre
            binding.userText.text = usuarioDatos.nombreUsuario
            val description = Description()
            description.text = "Peso vs Mes"
            binding.graficaUser.description = description

            val entries = ArrayList<Entry>()

            for(i in usuarioDatos.pesos){
                entries.add(Entry(entries.size.toFloat()+1f, i.toFloat()))
            }
            val dataSet = LineDataSet(entries, "Peso")
            dataSet.color = resources.getColor(R.color.cian_oscuro)
            dataSet.valueTextColor = resources.getColor(R.color.black)

            val xAxis = binding.graficaUser.xAxis
            xAxis.valueFormatter = object : ValueFormatter() {
                override fun getFormattedValue(value: Float): String {
                    return value.toInt().toString()
                }
            }
            xAxis.setGranularity(1f)

            val lineData = LineData(dataSet)
            binding.graficaUser.setBackgroundColor(Color.WHITE)
            binding.graficaUser.data = lineData
            binding.graficaUser.invalidate()
        }


        binding.buttonMenu.setOnClickListener {
            preference = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = preference.edit()

            val gson1 = Gson()
            val userSerializado = gson1.toJson(usuarioDatos)
            editor.putString(uid, userSerializado)
            editor.putBoolean("cambiosRealizadosHistorial", false)
            editor.apply()
            finish()
        }

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
                usuarioDatos.nombre = binding.nombreEdit.text.toString()
                usuarioDatos.nombreUsuario = binding.userEdit.text.toString()
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
        binding.buttonCerrar.setOnClickListener {
            preference = PreferenceManager.getDefaultSharedPreferences(this)
            val editor = preference.edit()

            val gson1 = Gson()
            val userSerializado = gson1.toJson(usuarioDatos)
            editor.putString(uid, userSerializado)
            editor.putBoolean("cambiosRealizadosHistorial", false)
            editor.apply()
            val auth = FirebaseAuth.getInstance()
            val activityManager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            val activities = activityManager.appTasks
            val myPackageName = packageName

            for (task in activities) {
                val baseActivity = task.taskInfo.baseActivity
                val packageName = baseActivity?.packageName

                if (packageName != null && packageName == myPackageName) {
                    task.finishAndRemoveTask()
                }
            }
            auth.signOut()
            val intent = Intent(this, LogInActivity::class.java)
            startActivity(intent)
        }

    }


}