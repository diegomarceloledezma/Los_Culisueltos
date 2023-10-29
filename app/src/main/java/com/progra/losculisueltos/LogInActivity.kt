package com.progra.losculisueltos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.progra.losculisueltos.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLogInBinding
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
                    val intent = Intent(this, MenuActivity::class.java)
                    startActivity(intent)
                } else{
                    Toast.makeText(this, "Error al iniciar sesión. Por favor, verifica tus credenciales.", Toast.LENGTH_SHORT).show()

                }
            }
    }
}