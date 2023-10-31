package com.progra.losculisueltos

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.gson.Gson
import com.progra.losculisueltos.databinding.ActivityLogInBinding
import com.progra.losculisueltos.dataclases.Historial
import com.progra.losculisueltos.dataclases.Rutinas

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
    private lateinit var preference: SharedPreferences
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.crearCuenta.setOnClickListener {
            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
        binding.inicioSesion.setOnClickListener {
            loginUser(binding.userEdit.text.toString(),binding.contraEdit.text.toString())
        }
    }

    fun loginUser(email: String, password: String){

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor, completa el correo y la contraseña", Toast.LENGTH_SHORT).show()
            return
        }

        auth.signInWithEmailAndPassword(email,password)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    preference = PreferenceManager.getDefaultSharedPreferences(this)
                    val editor = preference.edit()
                    var listHistorial: List<Historial> = mutableListOf()
                    var listRutinas: List<Rutinas> = mutableListOf()
                    val gson = Gson()
                    val listaSerializado = gson.toJson(listHistorial)
                    val listaSerializadoR = gson.toJson(listRutinas)
                    editor.putString(MenuActivity.CLAVE_HISTORIAL_LISTA, listaSerializado)
                    editor.apply()
                    editor.putString(MenuActivity.CLAVE_RUTINAS_LISTA, listaSerializadoR)
                    editor.apply()
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                } else{
                    Toast.makeText(this, "Error al iniciar sesión. Por favor, verifica tus credenciales.", Toast.LENGTH_SHORT).show()

                }
            }
    }
}