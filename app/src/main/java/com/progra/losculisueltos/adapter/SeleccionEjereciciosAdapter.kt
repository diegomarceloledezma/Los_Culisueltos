package com.progra.losculisueltos.adapter

import android.content.Context
import android.content.SharedPreferences
import android.graphics.drawable.ColorDrawable
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.progra.losculisueltos.R
import com.progra.losculisueltos.RutinaEjerciciosActivity
import com.progra.losculisueltos.databinding.ItemSelectEjercicioBinding
import com.progra.losculisueltos.dataclases.Ejercicios

class SeleccionEjereciciosAdapter: RecyclerView.Adapter<SeleccionEjereciciosAdapter.SeleccionEjereciciosViewHolder>(){
    private var context: Context? = null
    private var listaEjercicios = mutableListOf<Ejercicios>()
    private lateinit var preference: SharedPreferences
    lateinit var jsonMap: String
    var mapaEjercicios: MutableMap<Int, Boolean> = mutableMapOf()


    interface OnItemClickListener {
        fun onItemClick(ejercicios: Ejercicios)
    }

    private var onItemClickListener: ((Ejercicios) -> Unit)? = null

    fun setOnItemClickListener(listener: (Ejercicios) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ):SeleccionEjereciciosAdapter.SeleccionEjereciciosViewHolder {
        context = parent.context
        preference = PreferenceManager.getDefaultSharedPreferences(context)
        jsonMap = preference.getString(RutinaEjerciciosActivity.CLAVE_EJERCICIOS, null)?: ""

        if (jsonMap != "") {
            mapaEjercicios= Gson().fromJson(jsonMap, object : TypeToken<Map<Int, Boolean>>() {}.type)
        }
        return SeleccionEjereciciosViewHolder(
            ItemSelectEjercicioBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: SeleccionEjereciciosAdapter.SeleccionEjereciciosViewHolder,
        position: Int
    ) {
        val ejercicios = listaEjercicios[position]
        holder.binding(ejercicios)
        holder.setupItemClickListener(ejercicios)
    }

    override fun getItemCount(): Int = listaEjercicios.size

    inner class SeleccionEjereciciosViewHolder(private val binding: ItemSelectEjercicioBinding):
        RecyclerView.ViewHolder(binding.root){
            fun binding(data: Ejercicios){
                if (jsonMap != "") {
                    if(mapaEjercicios[data.id]?:false){
                        val backgroundColor = ContextCompat.getColor(context!!, R.color.azul_calro)
                        val backgroundDrawable = ColorDrawable(backgroundColor)
                        binding.ejercicioSelect.background = backgroundDrawable
                    }
                }
                binding.ejercicioSelect.text =  data.nombre
            }

        fun setupItemClickListener(data: Ejercicios) {
            binding.root.setOnClickListener {
                val backgroundColor = ContextCompat.getColor(context!!, R.color.azul_calro)
                val currentBackgroundColor = (binding.ejercicioSelect.background as? ColorDrawable)?.color ?: 0

                if (currentBackgroundColor == backgroundColor) {
                    val whiteBackgroundColor = ContextCompat.getColor(context!!, R.color.white)
                    val backgroundDrawable = ColorDrawable(whiteBackgroundColor)
                    binding.ejercicioSelect.background = backgroundDrawable
                } else {
                    val backgroundDrawable = ColorDrawable(backgroundColor)
                    binding.ejercicioSelect.background = backgroundDrawable
                }
                onItemClickListener?.invoke(data)
            }
        }

    }
    fun addListaEjercicios(nuevaListaEjercicios: List<Ejercicios>) {
        listaEjercicios.clear()
        listaEjercicios.addAll(nuevaListaEjercicios)
    }
}