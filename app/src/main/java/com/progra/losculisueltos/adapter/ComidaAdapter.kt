package com.progra.losculisueltos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.progra.losculisueltos.databinding.ItemComidasBinding
import com.progra.losculisueltos.dataclases.Comidas
import java.text.FieldPosition

class ComidaAdapter: RecyclerView.Adapter<ComidaAdapter.ComidaAdapterViewHolder>() {
    private var context: Context? = null
    private val listaComidas = mutableListOf<Comidas>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComidaAdapterViewHolder {
        context = parent.context
        return ComidaAdapterViewHolder(
            ItemComidasBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
                )
            )
        }

    override fun onBindViewHolder(Holder: ComidaAdapterViewHolder, position: Int){
        Holder.binding(listaComidas[position])
    }

    override fun getItemCount(): Int = listaComidas.size

    inner class ComidaAdapterViewHolder(private val binding: ItemComidasBinding): RecyclerView.ViewHolder(binding.root){
        fun binding(data: Comidas){
            binding.tituloComidaCalorias.text = data.nombre + " - " + data.calorias
            binding.textoPreparacion.text = data.preparacion
            binding.imagenComida.setImageResource(data.imagenC)

            val innerRecyclerView = binding.innerRecyclerView
            val ingredientesAdapter = IngredientesAdapter(data.ingredientes)
            innerRecyclerView.layoutManager = LinearLayoutManager(context)
            innerRecyclerView.adapter = ingredientesAdapter
        }
    }

    fun addComidas(nuevaListaComidas: List<Comidas>) {
        listaComidas.clear()
        listaComidas.addAll(nuevaListaComidas)
        }


}

