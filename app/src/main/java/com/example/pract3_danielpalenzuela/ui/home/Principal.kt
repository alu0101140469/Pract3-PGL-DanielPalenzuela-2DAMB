package com.example.pract3_danielpalenzuela.ui.home

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.pract3_danielpalenzuela.R
import com.example.pract3_danielpalenzuela.ui.objetos.ListaPrimerasAparicionesFiltrada
import com.example.pract3_danielpalenzuela.ui.objetos.Jugador
import com.example.pract3_danielpalenzuela.ui.objetos.ListaJugadores
import com.example.pract3_danielpalenzuela.ui.objetos.obtenerPrimerasAparicionesUnicas
import com.example.pract3_danielpalenzuela.ui.theme.Purple40
import com.example.pract3_danielpalenzuela.ui.rutas.Rutas
//import androidx.compose.material3.SearchBar

@Composable
fun PantallaPrincipal(navController: NavHostController?) {

    val context = LocalContext.current

    var filtroPrimeraAparicion: String by remember {
        mutableStateOf("")
    }
    var consulta: String by remember {
        mutableStateOf("")
    }
    var statusBotones: Boolean by remember {
        mutableStateOf(false)
    }
    var confirmarBorrado by remember {
        mutableStateOf(false)
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        BarraBusqueda(
            context = context,
            onQueryChange = { nuevaConsulta -> consulta = nuevaConsulta },
            onSearch = {
                filtroPrimeraAparicion = consulta
            }
        )

        Spacer(modifier = Modifier.padding(4.dp))

        JugadoresACards(
            jugadores = ListaJugadores.lista,
            filtroPrimeraAparicion = filtroPrimeraAparicion,
            buttonStatus = statusBotones
        )
    }

    if (confirmarBorrado && !statusBotones) {
        checkBorradoDialog(
            onCheckBorrado = {
                ListaJugadores.lista.removeAll { it.seleccionado }
                confirmarBorrado = false
            },
            onCancelar = {
                confirmarBorrado = false
            }
        )
    }

    if (navController != null) {
        funcButtons(navController) {
            statusBotones = !statusBotones
            confirmarBorrado = true
        }
    }
}

@Composable
fun funcButtons(navController: NavHostController, onBorrarClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        ExtendedFloatingActionButton(
            onClick = { navController.navigate(Rutas.PantallaFormulario.ruta) },
            modifier = Modifier
        ) {
            Text(text = "Añadir")
            Icon(
                painterResource(id = R.drawable.simbolomas),
                contentDescription = "Accion añadir"
            )
        }
        ExtendedFloatingActionButton(onClick = {
            onBorrarClick()
        }) {
            Text(text = "Borrar")
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Accion borrar"
            )
        }
    }
}

@Composable
fun checkBorradoDialog(
    onCheckBorrado: () -> Unit,
    onCancelar: () -> Unit
) {

    AlertDialog(
        onDismissRequest = { onCancelar() },
        title = {
            Text("Confirmar eliminación")
        },
        text = {
            Text("¿Confirmas que se quiere eliminar a los jugadores seleccionados?")
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onCheckBorrado()
                }
            ) {
                Text("Confirmar")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onCancelar()
                }
            ) {
                Text("Cancelar")
            }
        }
    )
}

@Composable
fun TarjetaContacto(
    colorFondo: Color,
    jugador: Jugador,
    buttonStatus: Boolean
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            DatosJugador(jugador, buttonStatus)
        }
    }
}

@Composable
fun DatosJugador(jugador: Jugador, buttonStatus: Boolean) {

    Row(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        val painterImg = painterResource(id = jugador.idImagen)
        Image(
            painter = painterImg,
            contentDescription = "Foto de ${jugador.nombre}",
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primary)
        )

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier
                .weight(1f)
                .wrapContentHeight()
        ) {

            Text(
                text = jugador.nombre,
                style = MaterialTheme.typography.headlineMedium
            )
            Text(
                text = "Primera aparición: ${jugador.primeraAparicion}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
            Text(
                text = "Año de la primera aparición: ${jugador.agnoAparicion}",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
            Text(
                text = "${jugador.edad} años",
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 4.dp)
            )
        }

        if (buttonStatus) {
            Spacer(modifier = Modifier.width(8.dp))
            var selecionado by remember {
                mutableStateOf(jugador.seleccionado)
            }
            Checkbox(
                checked = selecionado,
                onCheckedChange = { isChecked ->
                    jugador.seleccionado = isChecked
                    selecionado = isChecked
                },
                modifier = Modifier.padding(8.dp)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BarraBusqueda(
    context: Context,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit
) {
    var consulta by remember {
        mutableStateOf("")
    }
    var estadoBarra by remember {
        mutableStateOf(false)
    }
//    SearchBar(
//        query = consulta,
//        onQueryChange = {
//            consulta = it
//            onQueryChange(it)
//        },
//        onSearch = {
//            Toast.makeText(context, consulta, Toast.LENGTH_LONG).show()
//            onSearch()
//            estadoBarra = false
//        },
//        active = estadoBarra,
//        onActiveChange = { estadoBarra = it },
//        placeholder = { Text(text = "Buscador") },
//        trailingIcon = {
//            IconButton(onClick = { estadoBarra = false }) {
//                Icon(
//                    painter = painterResource(id = R.drawable.search),
//                    contentDescription = ""
//                )
//            }
//        }
//    ) {
//        LazyColumn {
//            ListaPrimerasAparicionesFiltrada.lista =
//                obtenerPrimerasAparicionesUnicas().filter {
//                    it.startsWith(
//                        consulta,
//                        true
//                    )
//                } as ArrayList<String>
//            items(ListaPrimerasAparicionesFiltrada.lista) {
//                ListItem(modifier = Modifier.clickable {
//                    consulta = it
//                    onQueryChange(it)
//                }, headlineContent = { Text(text = it) })
//            }
//        }
//    }
}

@Composable
fun JugadoresACards(jugadores: List<Jugador>, filtroPrimeraAparicion: String, buttonStatus: Boolean) {
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
    ) {
        items(jugadores) { jugador: Jugador ->
            if (filtroPrimeraAparicion.isEmpty()) {
                TarjetaContacto(
                    colorFondo = Purple40,
                    jugador = jugador,
                    buttonStatus = buttonStatus
                )
            } else if (jugador.primeraAparicion.startsWith(filtroPrimeraAparicion, true)) {
                TarjetaContacto(
                    colorFondo = Purple40,
                    jugador = jugador,
                    buttonStatus = buttonStatus
                )
            }
        }
    }
}