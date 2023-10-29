package com.progra.losculisueltos

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.progra.losculisueltos.databinding.RutinaItemBinding
import com.progra.losculisueltos.dataclases.Rutinas

class RutinasAdapter :
    RecyclerView.Adapter<RutinasAdapter.RutinasAdapterViewHolder>() {
    private var context: Context? = null
    private val listaRutinas = mutableListOf<Rutinas>()

    // Interfaz para manejar clics en los elementos de la lista
    interface OnItemClickListener {
        fun onItemClick(rutina: Rutinas)
    }

    private var onItemClickListener: ((Rutinas) -> Unit)? = null

    // Método para configurar el listener
    fun setOnItemClickListener(listener: (Rutinas) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RutinasAdapterViewHolder {
        context = parent.context
        return RutinasAdapterViewHolder(
            RutinaItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: RutinasAdapterViewHolder, position: Int) {
        val rutina = listaRutinas[position]
        holder.binding(rutina)

        holder.setupItemClickListener(rutina)
    }

    override fun getItemCount(): Int = listaRutinas.size

    inner class RutinasAdapterViewHolder(private val binding: RutinaItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun binding(data: Rutinas) {
            binding.textViewRutina.text = data.nombre

        }
        fun setupItemClickListener(data: Rutinas) {
            binding.root.setOnClickListener {
                onItemClickListener?.invoke(data)
            }
        }

    }

    fun addRutinas(nuevaListaRutinas: List<Rutinas>) {
        listaRutinas.clear()
        listaRutinas.addAll(nuevaListaRutinas)
    }
}
