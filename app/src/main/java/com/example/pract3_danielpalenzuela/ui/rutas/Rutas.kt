package com.example.pract3_danielpalenzuela.ui.rutas

sealed class Rutas (val ruta: String){

    object PantallaPrincipal: Rutas("principal")
    object PantallaFormulario: Rutas("formulario")

}