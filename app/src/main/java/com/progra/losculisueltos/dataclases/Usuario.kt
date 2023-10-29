package com.progra.losculisueltos.dataclases

data class Usuario(
    val id: String,
    val nombre: String,
    var nombreUsuario: String,
    val correo:String,
    var password: String,
    var historial: List<Rutinas>,
    var rutinas: List<Rutinas>,
    var pesos: List<PesosInfo>

)
