package com.example.pract3_danielpalenzuela

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.pract3_danielpalenzuela.ui.metodos.Metodos
import com.example.pract3_danielpalenzuela.ui.theme.Pract3DanielPalenzuelaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Pract3DanielPalenzuelaTheme {

                Surface(
                    modifier = Modifier.fillMaxWidth(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Metodos.GraphNavegacion()
                }

            }
        }
    }
}