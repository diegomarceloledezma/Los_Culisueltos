package com.progra.losculisueltos

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.progra.losculisueltos.adapter.ComidaAdapter
import com.progra.losculisueltos.databinding.ActivityComidaBinding
import com.progra.losculisueltos.dataclases.Comidas

class ComidaActivity : AppCompatActivity() {
    lateinit var binding: ActivityComidaBinding
    val context: Context = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityComidaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.cardViewCalculadoraCalorica.setOnClickListener {
            val intent: Intent = Intent(context, CalculadoraActivity::class.java)
            startActivity(intent)
        }
        binding.buttonUser.setOnClickListener {
            val intent: Intent = Intent(context, PerfilActivity::class.java)
            startActivity(intent)
        }
        binding.cardViewDesayuno.setOnClickListener{
            val intent: Intent = Intent(context, ComidaIndividualActivity::class.java)
            startActivity(intent)
        }
    }


}