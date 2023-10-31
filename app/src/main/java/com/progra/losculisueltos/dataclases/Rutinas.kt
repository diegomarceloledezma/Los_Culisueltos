package com.progra.losculisueltos.dataclases

import java.io.Serializable

data class Rutinas(
    var nombre: String,
    var ejercicios: MutableMap<Int, Boolean>,
    var infoEjercio: MutableList<EjercicioInfo>
) : Serializable
