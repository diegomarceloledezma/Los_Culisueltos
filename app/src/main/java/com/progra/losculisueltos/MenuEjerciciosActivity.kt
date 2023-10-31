package com.progra.losculisueltos

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.progra.losculisueltos.databinding.ActivityMenuEjerciciosBinding

class MenuEjerciciosActivity : AppCompatActivity() {
    lateinit var binding: ActivityMenuEjerciciosBinding
    val context: Context = this
    val ejerciciosBase = ListaDeEjerciciosProvider()
    val ejercicioPierna = ejerciciosBase.listaPiernas
    val ejercicioPecho = ejerciciosBase.listaPecho
    val ejercicioEspalda = ejerciciosBase.listaEspalda
    val ejercicioBrazo = ejerciciosBase.listaBrazos
    val ejercicioHombros = ejerciciosBase.listaHombros
    val ejercicioAbdominal = ejerciciosBase.listaAbdominales
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMenuEjerciciosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonMenu.setOnClickListener {
            finish()
        }
        binding.buttonUser.setOnClickListener {
            val intent: Intent = Intent(context, PerfilActivity::class.java)
            startActivity(intent)
        }

        binding.abdominalesMenu.setOnClickListener {
            val intent: Intent = Intent(context, RecyclerViewMenuListaEjerciciosActivity::class.java)
            val arrayList = ArrayList(ejercicioAbdominal)
            intent.putExtra(CLAVE_LISTA_EJERCICIOS, arrayList)
            startActivity(intent)
        }
        binding.pechoMenu.setOnClickListener {
            val intent: Intent = Intent(context, RecyclerViewMenuListaEjerciciosActivity::class.java)
            val arrayList = ArrayList(ejercicioPecho)
            intent.putExtra(CLAVE_LISTA_EJERCICIOS, arrayList)
            startActivity(intent)
        }
        binding.piernasMenu.setOnClickListener {
            val intent: Intent = Intent(context, RecyclerViewMenuListaEjerciciosActivity::class.java)
            val arrayList = ArrayList(ejercicioPierna)
            intent.putExtra(CLAVE_LISTA_EJERCICIOS, arrayList)
            startActivity(intent)
        }
        binding.brazosMenu.setOnClickListener {
            val intent: Intent = Intent(context, RecyclerViewMenuListaEjerciciosActivity::class.java)
            val arrayList = ArrayList(ejercicioBrazo)
            intent.putExtra(CLAVE_LISTA_EJERCICIOS, arrayList)
            startActivity(intent)
        }
        binding.hombroMenu.setOnClickListener {
            val intent: Intent = Intent(context, RecyclerViewMenuListaEjerciciosActivity::class.java)
            val arrayList = ArrayList(ejercicioHombros)
            intent.putExtra(CLAVE_LISTA_EJERCICIOS, arrayList)
            startActivity(intent)
        }
        binding.espaldaMenu.setOnClickListener {
            val intent: Intent = Intent(context, RecyclerViewMenuListaEjerciciosActivity::class.java)
            val arrayList = ArrayList(ejercicioEspalda)
            intent.putExtra(CLAVE_LISTA_EJERCICIOS, arrayList)
            startActivity(intent)
        }

    }
    companion object{
        val CLAVE_LISTA_EJERCICIOS = "clave_lista"
    }
}