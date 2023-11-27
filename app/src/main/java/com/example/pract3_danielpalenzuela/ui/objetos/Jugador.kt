package com.example.pract3_danielpalenzuela.ui.objetos

data class Jugador(

    val idJugador: Int,
    val idImagen: Int,
    val nombre: String,
    val edad: Int,
    val agnoAparicion: String,
    val primeraAparicion: String,
    val popularidad: String,
    var seleccionado: Boolean = false

)