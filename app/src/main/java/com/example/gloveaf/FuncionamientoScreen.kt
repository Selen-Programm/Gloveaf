package com.example.gloveaf

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gloveaf.R
import com.example.gloveaf.ColorCrema
import com.example.gloveaf.ColorAzulOscuro
import kotlinx.coroutines.delay

@Composable
fun FuncionamientoScreen(onConfigClick: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) {
        delay(80)
        visible = true
    }
    val alpha by animateFloatAsState(targetValue = if (visible) 1f else 0f, animationSpec = tween(500, easing = EaseOut), label = "FuncionamientoAlpha")
    val offsetPanel by animateFloatAsState(targetValue = if (visible) 0f else 30f, animationSpec = tween(500, easing = EaseOut), label = "PanelOffset")

    Column(modifier = Modifier.fillMaxSize().background(ColorCrema).alpha(alpha)) {
        Box(modifier = Modifier.fillMaxWidth().weight(0.8f), contentAlignment = Alignment.Center) {
            Image(painter = painterResource(id = R.drawable.logogloveaf), contentDescription = "Logo", modifier = Modifier.fillMaxWidth(0.9f))
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.6f)
                .graphicsLayer {
                    translationY = offsetPanel
                    shape = GenericShape { size, _ ->
                        moveTo(0f, 100f)
                        lineTo(size.width / 2f, 0f)
                        lineTo(size.width, 100f)
                        lineTo(size.width, size.height)
                        lineTo(0f, size.height)
                        close()
                    }
                    clip = true
                }
                .background(ColorAzulOscuro)
        ) {
            Column(
                modifier = Modifier.fillMaxSize().padding(top = 60.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text("Dispositivo vinculado", color = Color.White, fontSize = 20.sp)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Predicción de palabras", color = Color.White, modifier = Modifier.padding(bottom = 8.dp))
                    Box(
                        modifier = Modifier.fillMaxWidth(0.8f).height(180.dp).background(Color(0xFF8E9B9E), RoundedCornerShape(20.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        val infiniteTransition = rememberInfiniteTransition(label = "Parpadeo")
                        val alphaEsperando by infiniteTransition.animateFloat(
                            initialValue = 0.4f, targetValue = 0.8f,
                            animationSpec = infiniteRepeatable(animation = tween(1200, easing = EaseInOut), repeatMode = RepeatMode.Reverse),
                            label = "AlphaEsperando"
                        )
                        Text("Esperando...", color = Color.White.copy(alpha = alphaEsperando))
                    }
                }
                TextButton(onClick = onConfigClick, modifier = Modifier.padding(bottom = 30.dp)) {
                    Text("Configuración", color = Color.White, fontSize = 18.sp)
                }
            }
        }
    }
}