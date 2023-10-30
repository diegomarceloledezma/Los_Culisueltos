package com.progra.losculisueltos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.progra.losculisueltos.databinding.ItemComidasBinding
import com.progra.losculisueltos.databinding.ItemOpcionComidasBinding
import com.progra.losculisueltos.databinding.RutinaItemBinding
import com.progra.losculisueltos.dataclases.Comidas
import com.progra.losculisueltos.dataclases.Rutinas

class MenuComidasAdapter : RecyclerView.Adapter<MenuComidasAdapter.MenuComidasAdapterViewHolder>() {
    private var context: Context? = null
    private val listaComidas = mutableListOf<Comidas>()


    interface OnItemClickListener {
        fun onItemClick(comidas: Comidas)
    }

    private var onItemClickListener: ((Comidas) -> Unit)? = null


    fun setOnItemClickListener(listener: (Comidas) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MenuComidasAdapterViewHolder {
        context = parent.context
        return MenuComidasAdapterViewHolder(
            ItemOpcionComidasBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MenuComidasAdapterViewHolder, position: Int) {
        val comida = listaComidas[position]
        holder.binding(comida)

        holder.setupItemClickListener(comida)
    }

    override fun getItemCount(): Int = listaComidas.size

    inner class MenuComidasAdapterViewHolder(private val binding: ItemOpcionComidasBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(data: Comidas) {
            binding.opcionCommida.text = "${data.nombre} - ${data.calorias}"

        }

        fun setupItemClickListener(data: Comidas) {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(data)
            }
        }

    }

    fun addComidas(nuevaListaComidas: List<Comidas>) {
        listaComidas.clear()
        listaComidas.addAll(nuevaListaComidas)
    }
}