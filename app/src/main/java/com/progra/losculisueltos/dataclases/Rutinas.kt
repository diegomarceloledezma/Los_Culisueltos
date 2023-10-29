package com.progra.losculisueltos.dataclases

import java.io.Serializable

data class Rutinas(
    val id: Int,
    var nombre: String,
    var ejercicios: List<EjercicioInfo>
) : Serializable
