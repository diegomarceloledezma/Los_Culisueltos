package com.progra.losculisueltos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.progra.losculisueltos.R
import com.progra.losculisueltos.databinding.ItemSelectEjercicioBinding
import com.progra.losculisueltos.dataclases.Ejercicios

class SeleccionEjereciciosAdapter: RecyclerView.Adapter<SeleccionEjereciciosAdapter.SeleccionEjereciciosViewHolder>(){
    private var context: Context? = null
    private var listaEjercicios = mutableListOf<Ejercicios>()


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
                binding.ejercicioSelect.text =  data.nombre
            }

        fun setupItemClickListener(data: Ejercicios) {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(data)
            }
        }

    }
    fun addListaEjercicios(nuevaListaEjercicios: List<Ejercicios>) {
        listaEjercicios.clear()
        listaEjercicios.addAll(nuevaListaEjercicios)
    }
}