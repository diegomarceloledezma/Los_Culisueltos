package com.progra.losculisueltos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.progra.losculisueltos.databinding.ItemMenuListaDeEjerciciosBinding
import com.progra.losculisueltos.dataclases.Ejercicios

class MenuListaEjerciciosAdapter  : RecyclerView.Adapter<MenuListaEjerciciosAdapter.MenuListaEjerciciosAdapterViewHolder>() {


    private var context: Context? = null
    private val listaMenuEjercicios = mutableListOf<Ejercicios>()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuListaEjerciciosAdapter.MenuListaEjerciciosAdapterViewHolder {
        context = parent.context
        return MenuListaEjerciciosAdapterViewHolder(
            ItemMenuListaDeEjerciciosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: MenuListaEjerciciosAdapter.MenuListaEjerciciosAdapterViewHolder,
        position: Int
    ) {
        holder.binding(listaMenuEjercicios[position])
    }

    override fun getItemCount(): Int = listaMenuEjercicios.size

    inner class MenuListaEjerciciosAdapterViewHolder(private val binding: ItemMenuListaDeEjerciciosBinding):
        RecyclerView.ViewHolder(binding.root) {
        fun binding(data: Ejercicios){
            binding.textViewCardio.text = data.nombre
        }

    }

    fun addListaEjercicios(nuevaListaEjercicios: List<Ejercicios>){
        listaMenuEjercicios.clear()
        listaMenuEjercicios.addAll(nuevaListaEjercicios)
    }
}