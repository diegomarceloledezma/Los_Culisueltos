package com.progra.losculisueltos.recyclerviews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.progra.losculisueltos.ComidaActivity
import com.progra.losculisueltos.PerfilActivity
import com.progra.losculisueltos.recyclerviews.ComidaIndividualActivity.Companion.CLAVE_COMIDA
import com.progra.losculisueltos.adapter.MenuComidasAdapter
import com.progra.losculisueltos.databinding.ActivityMenuComidasBinding
import com.progra.losculisueltos.dataclases.Comidas

class MenuComidasActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMenuComidasBinding
    private val menuComidasAdapter by lazy {MenuComidasAdapter()}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuComidasBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val comidas: List<Comidas> = intent.getSerializableExtra(ComidaActivity.CLAVE_LISTA) as List<Comidas>
        iniciarRecyclerView(comidas)
        binding.buttonMenu.setOnClickListener {
            finish()
        }
        binding.buttonUser.setOnClickListener {
            val intent: Intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }
    }
    fun iniciarRecyclerView(comidas: List<Comidas>) {
        menuComidasAdapter.addComidas(comidas)
        binding.comidasRecycler.apply{
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter=menuComidasAdapter
            menuComidasAdapter.setOnItemClickListener { comida ->
                val intent = Intent(this@MenuComidasActivity, ComidaIndividualActivity::class.java)
                intent.putExtra(CLAVE_COMIDA, comida)
                startActivity(intent)
            }
        }
        menuComidasAdapter.notifyDataSetChanged()
    }
}