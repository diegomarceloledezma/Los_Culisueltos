package com.progra.losculisueltos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.progra.losculisueltos.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        auth = Firebase.auth
        binding.crearCuenta.setOnClickListener {
            clickCrearCuenta(binding.correoEdit.text.toString(),binding.contraEdit.text.toString())
        }
    }

    fun clickCrearCuenta(email: String, contrasena: String){
        auth.createUserWithEmailAndPassword(email, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val intent = Intent(this, LogInActivity::class.java)
                    startActivity(intent)
                } else {

                    val intent = Intent(this, SignUpActivity::class.java)
                    startActivity(intent)
                }
            }
    }

}