package com.progra.losculisueltos.dataclases

import java.io.Serializable

data class Ejercicios(
    var nombre: String,
    val imagenE: Int,
    var explicacion: String,
    var parteDelCuerpo: String,
    val imagenMusculo: Int,
    val id: String
): Serializable
