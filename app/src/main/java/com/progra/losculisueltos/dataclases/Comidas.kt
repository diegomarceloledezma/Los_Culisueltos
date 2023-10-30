package com.progra.losculisueltos.dataclases

data class Comidas(
    var nombre: String,
    var calorias: Int,
    var ingredientes: List<String>,
    val preparacion: String,
    val imagenC: Int
)
