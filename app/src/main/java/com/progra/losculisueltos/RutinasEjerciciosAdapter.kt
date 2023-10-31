package com.progra.losculisueltos

import android.content.Context
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.reflect.TypeToken
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.google.gson.Gson
import com.progra.losculisueltos.databinding.EjercicioRutinaItemBinding
import com.progra.losculisueltos.dataclases.Ejercicios
import com.progra.losculisueltos.dataclases.Rutinas


class RutinasEjerciciosAdapter : RecyclerView.Adapter<RutinasEjerciciosAdapter.RutinasEjerciciosAdapterViewHolder>() {

    private var context: Context? = null
    private val listaEjercicos = mutableListOf<Ejercicios>()



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
    ): RutinasEjerciciosAdapter.RutinasEjerciciosAdapterViewHolder {
        context = parent.context

        return RutinasEjerciciosAdapterViewHolder(
            EjercicioRutinaItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }
    override fun onBindViewHolder(holder: RutinasEjerciciosAdapter.RutinasEjerciciosAdapterViewHolder, position: Int) {
        val rutina = listaEjercicos[position]
        holder.binding(rutina)

        holder.setupItemClickListener(rutina)
    }

    override fun getItemCount(): Int = listaEjercicos.size

    fun getEjercicioAtPosition(position: Int): Ejercicios {
        return listaEjercicos[position]
    }

    inner class RutinasEjerciciosAdapterViewHolder(private val binding: EjercicioRutinaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val pesoEjercicio = binding.pesoEjer
        val serEjercicio = binding.serEjer
        val repEjercicio = binding.repEjer
        fun binding(data: Ejercicios) {

            binding.nameEjer.text = data.nombre

        }

        fun setupItemClickListener(data: Ejercicios) {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(data)
            }
        }

    }
    fun addRutinas(nuevaListaEjercicios: List<Ejercicios>) {
        listaEjercicos.clear()
        listaEjercicos.addAll(nuevaListaEjercicios)
    }
}