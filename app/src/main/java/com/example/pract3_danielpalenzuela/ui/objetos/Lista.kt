package com.example.pract3_danielpalenzuela.ui.objetos

import com.example.pract3_danielpalenzuela.R

object ListaJugadores {

    var lista: ArrayList<Jugador> = ArrayList()

    init {
        fillListaJugadores()
    }

    private fun fillListaJugadores() {

        addJugador(Jugador(idJugador = 1, idImagen = R.drawable.mario, nombre = "Mario", edad = 26, agnoAparicion = "1981", primeraAparicion = "Donkey Kong", popularidad = "10"))
        addJugador(Jugador(idJugador = 2, idImagen = R.drawable.luigi, nombre = "Luigi", edad = 26, agnoAparicion = "1983", primeraAparicion = "Mario Bros.", popularidad = "9"))
        addJugador(Jugador(idJugador = 3, idImagen = R.drawable.peach, nombre = "Peach", edad = 24, agnoAparicion = "1985", primeraAparicion = "Super Mario Bros.", popularidad = "10"))
        addJugador(Jugador(idJugador = 4, idImagen = R.drawable.daisy, nombre = "Daisy", edad = 25, agnoAparicion = "1989", primeraAparicion = "Super Mario Land", popularidad = "6"))
        addJugador(Jugador(idJugador = 5, idImagen = R.drawable.wario, nombre = "Wario", edad = 31, agnoAparicion = "1992", primeraAparicion = "Super Mario Land 2", popularidad = "5"))
        addJugador(Jugador(idJugador = 6, idImagen = R.drawable.waluigi, nombre = "Waluigi", edad = 34, agnoAparicion = "2000", primeraAparicion = "Mario Tennis", popularidad = "Ninguna"))
        addJugador(Jugador(idJugador = 7, idImagen = R.drawable.rosalina, nombre = "Rosalina", edad = 28, agnoAparicion = "2007", primeraAparicion = "Super Mario Galaxy", popularidad = "7"))
        addJugador(Jugador(idJugador = 8, idImagen = R.drawable.yoshi, nombre = "Yoshi", edad = 105, agnoAparicion = "1990", primeraAparicion = "Super Mario World", popularidad = "8"))
        addJugador(Jugador(idJugador = 9, idImagen = R.drawable.toad, nombre = "Toad", edad = 15, agnoAparicion = "1985", primeraAparicion = "Super Mario Bros.", popularidad = "4"))
        addJugador(Jugador(idJugador = 10,idImagen = R.drawable.bowser, nombre = "Bowser", edad = 33, agnoAparicion = "1985", primeraAparicion = "Super Mario Bros.", popularidad = "7"))

    }

    fun addJugador(jugador: Jugador) {
        lista.add(jugador)
    }
}


fun obtenerPrimerasAparicionesUnicas(): List<String> {
    return ListaJugadores.lista.map { it.primeraAparicion }.distinct()
}

object ListaPrimerasAparicionesFiltrada {
    var lista: ArrayList<String> = ArrayList()
}


object ListaIdBorrar {
    var lista: ArrayList<Jugador> = ArrayList()
}

object consultaGlobal {
    var texto = ""
}