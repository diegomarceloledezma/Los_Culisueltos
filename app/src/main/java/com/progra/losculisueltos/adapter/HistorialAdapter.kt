package com.progra.losculisueltos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.progra.losculisueltos.databinding.ItemHistorialBinding
import com.progra.losculisueltos.dataclases.Historial
import com.progra.losculisueltos.dataclases.Rutinas

class HistorialAdapter :
    RecyclerView.Adapter<HistorialAdapter.HistorialAdapterViewHolder>(){
    private var context: Context? = null
    private val listaHistorial = mutableListOf<Historial>()


    interface OnItemClickListener{
        fun onItemClick(historial: Historial)
    }

    private var onItemClickListener: ((Historial) -> Unit)? = null

    fun setOnItemClickListener(listener: (Historial) -> Unit){
        onItemClickListener = listener
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistorialAdapterViewHolder {
        context = parent.context
        return HistorialAdapterViewHolder(
            ItemHistorialBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: HistorialAdapterViewHolder, position: Int) {
        val historial = listaHistorial[position]
        holder.binding(historial)

        holder.setupItemClickListener(historial)
    }

    override fun getItemCount(): Int = listaHistorial.size

    inner class HistorialAdapterViewHolder(private val binding: ItemHistorialBinding):
        RecyclerView.ViewHolder(binding.root){

            fun binding(data: Historial){
                binding.textViewHistorial.text = data.fecha
            }

            fun setupItemClickListener(data : Historial){
                binding.root.setOnClickListener{
                    onItemClickListener?.invoke(data)
                }
            }
    }

    fun addHistorial(nuevaListaHistorial: List<Historial>) {
        listaHistorial.clear()
        listaHistorial.addAll(nuevaListaHistorial)
    }



}