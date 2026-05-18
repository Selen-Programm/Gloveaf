package com.example.gloveaf

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gloveaf.R
import com.example.gloveaf.ColorAzulOscuro
import kotlinx.coroutines.delay

@Composable
fun PantallaDeCarga() {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(100)
        visible = true
    }
    val alpha by animateFloatAsState(
        targetValue = if (visible) 1f else 0f,
        animationSpec = tween(700, easing = EaseOut),
        label = "LogoCargaAlpha"
    )
    val scale by animateFloatAsState(
        targetValue = if (visible) 1f else 0.88f,
        animationSpec = tween(700, easing = EaseOutBack),
        label = "LogoCargaScale"
    )

    Box(
        modifier = Modifier.fillMaxSize().background(ColorAzulOscuro),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.icongloveaf_claro),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxWidth(0.6f)
                .alpha(alpha)
                .graphicsLayer(scaleX = scale, scaleY = scale),
            contentScale = ContentScale.Fit
        )
    }
}