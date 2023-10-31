package com.progra.losculisueltos.dataclases

import java.io.Serializable

data class Usuario(
    var nombre: String,
    var nombreUsuario: String,
    var pesos: MutableList<Double>

): Serializable
