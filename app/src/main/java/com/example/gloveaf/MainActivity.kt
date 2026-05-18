package com.example.gloveaf

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import com.example.gloveaf.* // Asegúrate de que tus archivos de pantalla tengan este package
import com.example.gloveaf.ui.theme.GLOVEAFTheme
import kotlinx.coroutines.delay

// Colores globales y Formas (Shapes) que usan varias pantallas
val ColorCrema = Color(0xFFF5E9C9)
val ColorAzulOscuro = Color(0xFF0D3B66)

val OndaSuperiorShape = GenericShape { size, _ ->
    moveTo(0f, 0f)
    lineTo(size.width, 0f)
    lineTo(size.width, size.height * 0.7f)
    cubicTo(
        size.width * 0.8f, size.height * 1.1f,
        size.width * 0.2f, size.height * 0.5f,
        0f, size.height * 0.9f
    )
    close()
}

val OndaInferiorShape = GenericShape { size, _ ->
    moveTo(0f, size.height * 0.35f)
    cubicTo(
        size.width * 0.3f, size.height * 0.1f,
        size.width * 0.7f, size.height * 0.6f,
        size.width, size.height * 0.2f
    )
    lineTo(size.width, size.height)
    lineTo(0f, size.height)
    close()
}

enum class Pantalla { CARGA, MAIN, FUNCIONAMIENTO, CONFIGURACION }

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GLOVEAFTheme {
                var pantallaActual by remember { mutableStateOf(Pantalla.CARGA) }

                // --- LÓGICA DEL BOTÓN ATRÁS ---
                // Si no estamos en la pantalla principal, definimos a dónde volver.
                // Si estamos en MAIN, no activamos el BackHandler para que el sistema decida (o puedes bloquearlo).
                BackHandler(enabled = pantallaActual != Pantalla.MAIN && pantallaActual != Pantalla.CARGA) {
                    when (pantallaActual) {
                        Pantalla.CONFIGURACION -> pantallaActual = Pantalla.FUNCIONAMIENTO
                        Pantalla.FUNCIONAMIENTO -> pantallaActual = Pantalla.MAIN
                        else -> { /* No hace nada */ }
                    }
                }

                // Simulación de tiempo de carga
                LaunchedEffect(Unit) {
                    if (pantallaActual == Pantalla.CARGA) {
                        delay(2000)
                        pantallaActual = Pantalla.MAIN
                    }
                }

                // Navegación con animaciones
                AnimatedContent(
                    targetState = pantallaActual,
                    transitionSpec = {
                        fadeIn(animationSpec = tween(400, easing = EaseInOut)) togetherWith
                                fadeOut(animationSpec = tween(300, easing = EaseInOut))
                    },
                    label = "PantallaTransicion"
                ) { pantalla ->
                    when (pantalla) {
                        Pantalla.CARGA -> PantallaDeCarga()

                        Pantalla.MAIN -> MainScreen(
                            onVincularClick = { pantallaActual = Pantalla.FUNCIONAMIENTO }
                        )

                        Pantalla.FUNCIONAMIENTO -> FuncionamientoScreen(
                            onConfigClick = { pantallaActual = Pantalla.CONFIGURACION }
                        )

                        Pantalla.CONFIGURACION -> ConfiguracionScreen(
                            onBack = { pantallaActual = Pantalla.FUNCIONAMIENTO }
                        )
                    }
                }
            }
        }
    }
}