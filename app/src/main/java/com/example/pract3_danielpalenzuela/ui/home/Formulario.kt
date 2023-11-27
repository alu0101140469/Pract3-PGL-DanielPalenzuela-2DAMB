package com.example.pract3_danielpalenzuela.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pract3_danielpalenzuela.R
import com.example.pract3_danielpalenzuela.ui.objetos.ListaJugadores
import com.example.pract3_danielpalenzuela.ui.objetos.obtenerPrimerasAparicionesUnicas
import com.example.pract3_danielpalenzuela.ui.rutas.Rutas
import com.example.pract3_danielpalenzuela.ui.objetos.Jugador

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantallaFormulario(navController: NavHostController?) {

    var jugador by remember {
        mutableStateOf(
            Jugador(
                idJugador = 0,
                idImagen = R.drawable.mario,
                nombre = "",
                edad = 0,
                agnoAparicion = "",
                primeraAparicion = "",
                popularidad = "",
                seleccionado = false
            )
        )
    }

    var enviado by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (!enviado) {

            CamposFormulario(jugador) {
                jugador = it
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    ListaJugadores.addJugador(jugador)
                    enviado = true
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = "Enviar")
            }

        } else {
            Column {
                Text(
                    text = "Se ha agregado correctamente al nuevo jugador",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
                Button(
                    onClick = {
                        if (navController != null) {
                            navController.navigate(Rutas.PantallaPrincipal.ruta)
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(text = "Inicio")
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun CamposFormulario(jugador: Jugador, onJugadorChange: (Jugador) -> Unit) {

    OutlinedTextField(
        value = jugador.nombre,
        onValueChange = { onJugadorChange(jugador.copy(nombre = it)) },
        label = { Text(text = "Nombre") },
        modifier = Modifier.fillMaxWidth()
    )

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = jugador.edad.toString(),
        onValueChange = {
            onJugadorChange(jugador.copy(edad = it.toIntOrNull() ?: 0))
        },
        label = { Text(text = "Edad") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = jugador.agnoAparicion,
        onValueChange = { onJugadorChange(jugador.copy(agnoAparicion = it)) },
        label = { Text(text = "Año de la primera aparición") },
        modifier = Modifier.fillMaxWidth(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone)
    )

    Spacer(modifier = Modifier.height(8.dp))

    OutlinedTextField(
        value = jugador.primeraAparicion,
        onValueChange = { onJugadorChange(jugador.copy(primeraAparicion = it)) },
        label = { Text(text = "Primera aparición") },
        modifier = Modifier.fillMaxWidth(),
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = "Mostrar opciones"
            )
        }
    )

    SliderPopularidad {
        onJugadorChange(jugador.copy(popularidad = it))
    }

    Spacer(modifier = Modifier.height(8.dp))

    var menuexpand by remember {
        mutableStateOf(false)
    }

    DropdownMenu(
        expanded = menuexpand,
        onDismissRequest = { menuexpand = true },
        modifier = Modifier.fillMaxWidth()
    ) {
        obtenerPrimerasAparicionesUnicas().forEach { opcion ->
            DropdownMenuItem(text = { Text(text = opcion) },
                onClick = {
                    onJugadorChange(jugador.copy(primeraAparicion = opcion))
                }
            )
        }
    }

}

@Composable
fun SliderPopularidad(funOnValueChange: (String) -> Unit) {
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Popularidad del público")
        Slider(
            value = sliderPosition,
            onValueChange = {
                sliderPosition = it
                if (sliderPosition.toInt() == 0) {
                    funOnValueChange(sliderPosition.toInt().toString())
                } else if (sliderPosition.toInt() > 9) {
                    funOnValueChange("10 o más puntos")
                } else if (sliderPosition.toInt() == 1) {
                    funOnValueChange("$sliderPosition punto")
                } else {
                    funOnValueChange("$sliderPosition puntos")
                }
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 9,
            valueRange = 0f..10f,
            modifier = Modifier.padding(30.dp, 0.dp, 30.dp, 0.dp)
        )
        if (sliderPosition.toInt() == 0) {
            Text(text = "Ninguna")
        } else if (sliderPosition.toInt() > 9) {
            Text(text = "10 o mas puntos")
        } else if (sliderPosition.toInt() == 1) {
            Text(text = "1 punto")
        } else {
            Text(text = "${sliderPosition.toInt()} puntos")
        }
    }
}