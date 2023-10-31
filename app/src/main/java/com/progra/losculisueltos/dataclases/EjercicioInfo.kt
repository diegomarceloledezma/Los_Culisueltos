package com.progra.losculisueltos.dataclases

import java.io.Serializable

data class EjercicioInfo(
    val ejercicio: Ejercicios,
    val repes: Int,
    val series: Int,
    val peso: Int
): Serializable
