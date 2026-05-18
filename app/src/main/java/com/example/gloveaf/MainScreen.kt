package com.example.gloveaf

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gloveaf.R
import kotlinx.coroutines.delay

@Composable
fun MainScreen(onVincularClick: () -> Unit) {
    val azulOscuro = Color(0xFF0D2C4B)

    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(80)
        visible = true
    }

    val alphaImagen by animateFloatAsState(targetValue = if (visible) 1f else 0f, animationSpec = tween(500, easing = EaseOut), label = "ImagenAlpha")
    val alphaLogo by animateFloatAsState(targetValue = if (visible) 1f else 0f, animationSpec = tween(500, delayMillis = 150, easing = EaseOut), label = "LogoAlpha")
    val offsetLogo by animateFloatAsState(targetValue = if (visible) 0f else 20f, animationSpec = tween(500, delayMillis = 150, easing = EaseOut), label = "LogoOffset")
    val alphaBoton by animateFloatAsState(targetValue = if (visible) 1f else 0f, animationSpec = tween(500, delayMillis = 280, easing = EaseOut), label = "BotonAlpha")
    val offsetBoton by animateFloatAsState(targetValue = if (visible) 0f else 16f, animationSpec = tween(500, delayMillis = 280, easing = EaseOut), label = "BotonOffset")

    var botonPresionado by remember { mutableStateOf(false) }
    val scaleBoton by animateFloatAsState(
        targetValue = if (botonPresionado) 0.96f else 1f,
        animationSpec = spring(stiffness = Spring.StiffnessMediumLow),
        label = "BotonScale"
    )

    Column(
        modifier = Modifier.fillMaxSize().background(Color(0xFFF5E9C9)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.mano),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(180.dp).alpha(alphaImagen),
            contentScale = ContentScale.FillBounds,
        )
        Spacer(modifier = Modifier.height(20.dp))
        Image(
            painter = painterResource(id = R.drawable.logogloveaf),
            contentDescription = "Logo",
            modifier = Modifier.fillMaxWidth(0.7f).height(150.dp).alpha(alphaLogo).graphicsLayer(translationY = offsetLogo)
        )
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = {
                botonPresionado = true
                onVincularClick()
            },
            colors = ButtonDefaults.buttonColors(containerColor = azulOscuro),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier
                .fillMaxWidth(0.8f)
                .height(55.dp)
                .alpha(alphaBoton)
                .graphicsLayer(translationY = offsetBoton, scaleX = scaleBoton, scaleY = scaleBoton)
        ) {
            Text("Vincular dispositivo", fontSize = 18.sp)
        }
        Text(
            "Toque para buscar",
            modifier = Modifier.padding(top = 10.dp, bottom = 40.dp).alpha(alphaBoton),
            color = azulOscuro
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Image(
            painter = painterResource(id = R.drawable.mano),
            contentDescription = null,
            modifier = Modifier.fillMaxWidth().height(180.dp).alpha(alphaImagen).graphicsLayer(rotationZ = 180f),
            contentScale = ContentScale.FillBounds
        )
    }
}