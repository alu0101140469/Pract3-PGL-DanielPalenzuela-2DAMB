package com.example.pract3_danielpalenzuela.ui.metodos

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.pract3_danielpalenzuela.ui.rutas.Rutas
import com.example.pract3_danielpalenzuela.ui.home.PantallaFormulario
import com.example.pract3_danielpalenzuela.ui.home.PantallaPrincipal

class Metodos {

    companion object {

        @Composable
        fun GraphNavegacion() {

            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = Rutas.PantallaPrincipal.ruta) {

                composable(Rutas.PantallaPrincipal.ruta) {
                    PantallaPrincipal(navController = navController)
                }

                composable(Rutas.PantallaFormulario.ruta) {
                    PantallaFormulario(navController = navController)
                }

            }

        }

    }
}