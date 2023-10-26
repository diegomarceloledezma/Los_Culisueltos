package com.progra.losculisueltos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.progra.losculisueltos.databinding.ActivityAcercaDeBinding
import com.progra.losculisueltos.fragment.MapaFragment

class AcercaDeActivity : AppCompatActivity() {
    lateinit var binding: ActivityAcercaDeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAcercaDeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val fragment = MapaFragment()
        supportFragmentManager.commit {
            replace(binding.mapaGym.id, fragment)
            setReorderingAllowed(true)
            addToBackStack("replacement")
        }
        setContentView(R.layout.activity_acerca_de)

    }
}