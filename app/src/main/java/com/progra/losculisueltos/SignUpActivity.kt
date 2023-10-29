package com.progra.losculisueltos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.progra.losculisueltos.databinding.ActivitySignUpBinding
import com.progra.losculisueltos.dataclases.Rutinas
import com.progra.losculisueltos.dataclases.Usuario
import com.google.firebase.firestore.FirebaseFirestore
import com.progra.losculisueltos.dataclases.PesosInfo

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

        val usuario = binding.userEdit.text.toString()
        val nombre = binding.nombreEdit.text.toString()
        val contrasena2 = binding.contraEdit2.text.toString()

        if (email.isEmpty() || usuario.isEmpty() || contrasena.isEmpty() || contrasena2.isEmpty() || nombre.isEmpty()) {
            Toast.makeText(this, "Por favor, completa todos los campos.", Toast.LENGTH_SHORT).show()
            return
        }


        if (!isValidEmail(email)) {
            Toast.makeText(this, "Correo electrónico no válido. Por favor, verifica tu correo.", Toast.LENGTH_SHORT).show()
            return
        }

        if (contrasena != contrasena2) {
            Toast.makeText(this, "Las contraseñas no coinciden. Por favor, verifica tus contraseñas.", Toast.LENGTH_SHORT).show()
            return
        }
        auth.createUserWithEmailAndPassword(email, contrasena)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val user = auth.currentUser
                    val uid = user?.uid

                    if (uid != null) {
                        val nuevoUsuario = Usuario(
                            id = uid,
                            nombre = binding.nombreEdit.text.toString(),
                            nombreUsuario = binding.userEdit.text.toString(),
                            correo = email,
                            password = "",
                            historial = emptyList<Rutinas>(),
                            rutinas = emptyList<Rutinas>(),
                            pesos = emptyList<PesosInfo>()
                        )

                        guardarUsuarioEnFirestore(uid, nuevoUsuario)

                        val intent = Intent(this, LogInActivity::class.java)
                        startActivity(intent)
                    }
                } else {
                    val error = task.exception
                    Toast.makeText(this, "Error al crear la cuenta", Toast.LENGTH_SHORT).show()
                }
            }
    }
    private fun isValidEmail(email: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }

    fun guardarUsuarioEnFirestore(uid: String, usuario: Usuario) {
        val db = FirebaseFirestore.getInstance()

        val usuarioRef = db.collection("usuarios").document(uid)

        usuarioRef.set(usuario)
            .addOnSuccessListener {
                Toast.makeText(this, "Cuenta Creada Correctamente", Toast.LENGTH_SHORT).show()
            }

    }

}