package com.progra.losculisueltos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.progra.losculisueltos.databinding.EjercicioRutinaItemBinding
import com.progra.losculisueltos.databinding.ItemHistorialBinding
import com.progra.losculisueltos.databinding.ItemHistorialEjerciciosBinding
import com.progra.losculisueltos.dataclases.Ejercicios
import com.progra.losculisueltos.dataclases.Historial

class HistorialEjerciciosAdapter: RecyclerView.Adapter<HistorialEjerciciosAdapter.HistorialEjerciciosAdapterViewHolder>(){

    private var context: Context? = null
    private val listaHistorialEjercicos = mutableListOf<Historial>()


    interface OnItemClickListener {
        fun onItemClick(historial: Historial)
    }

    private var onItemClickListener: ((Historial) -> Unit)? = null

    fun setOnItemClickListener(listener: (Historial) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistorialEjerciciosAdapter.HistorialEjerciciosAdapterViewHolder {
        context = parent.context
        return HistorialEjerciciosAdapterViewHolder(
            ItemHistorialEjerciciosBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }



    override fun onBindViewHolder(holder: HistorialEjerciciosAdapter.HistorialEjerciciosAdapterViewHolder, position: Int) {
        val history = listaHistorialEjercicos[position]
        holder.binding(history)

        holder.setupItemClickListener(history)
    }

    override fun getItemCount(): Int = listaHistorialEjercicos.size
    inner class HistorialEjerciciosAdapterViewHolder(private val binding: ItemHistorialEjerciciosBinding):
        RecyclerView.ViewHolder(binding.root){
        fun binding(data: Historial) {
            binding.nameEjer.text = data.fecha

        }

        fun setupItemClickListener(data: Historial) {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(data)
            }
        }
    }

    fun addHistorial2(nuevaListaEjerciciosHistorial: List<Historial>) {
        listaHistorialEjercicos.clear()
        listaHistorialEjercicos.addAll(nuevaListaEjerciciosHistorial)
    }
}
