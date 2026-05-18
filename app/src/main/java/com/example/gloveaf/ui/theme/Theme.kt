package com.example.gloveaf.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary = AzulGloveaf,      // Botones y elementos principales
    secondary = PurpleGrey80,
    tertiary = Crema,
    background = FondoCrema,    // Fondo de toda la app
    surface = FondoCrema,
    onPrimary = androidx.compose.ui.graphics.Color.White,
    onBackground = AzulGloveaf,
    onSurface = AzulGloveaf
)

@Composable
fun GLOVEAFTheme(
    content: @Composable () -> Unit
) {
    // Forzamos el uso de LightColorScheme para que siempre se vea crema
    // ignorando si el usuario tiene el "Modo Oscuro" activado por ahora.
    MaterialTheme(
        colorScheme = LightColorScheme,
        typography = Typography,
        content = content
    )
}