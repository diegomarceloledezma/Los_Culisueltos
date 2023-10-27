package com.progra.losculisueltos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.progra.losculisueltos.databinding.ActivityMenuBinding


class MenuActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.nutricion.setOnClickListener {
            val intent = Intent(this, ComidaActivity::class.java)
            startActivity(intent)
        }
    }
}