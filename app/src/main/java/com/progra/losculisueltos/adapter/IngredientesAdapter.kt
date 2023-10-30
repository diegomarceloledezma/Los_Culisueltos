package com.progra.losculisueltos.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.progra.losculisueltos.databinding.ItemIngredientesBinding

class IngredientesAdapter(private val ingredientes: List<String>) : RecyclerView.Adapter<IngredientesAdapter.IngredientesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IngredientesViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = ItemIngredientesBinding.inflate(inflater, parent, false)
        return IngredientesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: IngredientesViewHolder, position: Int) {
        val ingrediente = ingredientes[position]
        holder.bind(ingrediente)
    }

    override fun getItemCount(): Int = ingredientes.size

    inner class IngredientesViewHolder(private val binding: ItemIngredientesBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(ingrediente: String) {
            binding.itemIngredientes.text = ingrediente
        }
    }
}