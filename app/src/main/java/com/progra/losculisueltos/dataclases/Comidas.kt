package com.progra.losculisueltos.dataclases

import java.io.Serializable

data class Comidas(
    var nombre: String,
    var calorias: Int,
    var ingredientes: String,
    val preparacion: String,
    val imagenC: Int
): Serializable
