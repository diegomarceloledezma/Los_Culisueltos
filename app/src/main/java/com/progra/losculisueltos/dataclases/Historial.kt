package com.progra.losculisueltos.dataclases

import java.io.Serializable

data class Historial(
    val fecha: String,
    val rutinas: Rutinas
): Serializable
