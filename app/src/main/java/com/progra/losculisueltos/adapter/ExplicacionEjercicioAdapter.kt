package com.progra.losculisueltos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.progra.losculisueltos.R
import com.progra.losculisueltos.databinding.ItemExplicacionEjercicioBinding
import com.progra.losculisueltos.dataclases.Ejercicios


class ExplicacionEjercicioAdapter :
    RecyclerView.Adapter<ExplicacionEjercicioAdapter.ExplicacionEjercicioAdapterViewHolder>() {

    private var context: Context? = null
    private val listaEjercicios = mutableListOf<Ejercicios>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ExplicacionEjercicioAdapter.ExplicacionEjercicioAdapterViewHolder{
        context = parent.context
        return ExplicacionEjercicioAdapterViewHolder(
            ItemExplicacionEjercicioBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(
        holder: ExplicacionEjercicioAdapter.ExplicacionEjercicioAdapterViewHolder,
        position: Int
    ) {
        holder.binding(listaEjercicios[position])
    }

   inner class ExplicacionEjercicioAdapterViewHolder(private val binding: ItemExplicacionEjercicioBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun binding(data: Ejercicios){
            binding.textViewTitulo.text = data.nombre
            binding.textViewExplicacion.text =  data.explicacion
            context?.let {
                Glide
                    .with(it)
                    .load(data.imagenE)
                    .into(binding.imageViewGif)
            }
        }
    }

    override fun getItemCount(): Int = listaEjercicios.size



    fun addEjercicios(nuevaListaEjercicios: List<Ejercicios>){
        listaEjercicios.clear()
        listaEjercicios.addAll(nuevaListaEjercicios)
    }
}