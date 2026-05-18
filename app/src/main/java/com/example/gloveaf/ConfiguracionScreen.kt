package com.example.gloveaf

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gloveaf.*
import kotlinx.coroutines.delay

@Composable
fun BotonPildora(texto: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = ColorCrema),
        shape = RoundedCornerShape(25.dp),
        modifier = Modifier.fillMaxWidth(0.8f).padding(vertical = 4.dp).height(50.dp)
    ) {
        Text(texto, color = ColorAzulOscuro, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun ConfiguracionScreen(onBack: () -> Unit) {
    var seccion by remember { mutableStateOf("MENU") }

    AnimatedContent(
        targetState = seccion,
        transitionSpec = {
            fadeIn(tween(350, easing = EaseOut)) + slideInHorizontally(
                initialOffsetX = { if (targetState == "MENU") -40 else 40 },
                animationSpec = tween(350, easing = EaseOut)
            ) togetherWith fadeOut(tween(250))
        },
        label = "SeccionTransicion"
    ) { seccionActual ->
        when (seccionActual) {
            "MENU"          -> ConfigMenuContent(onSeccionChange = { seccion = it }, onBack = onBack)
            "MANO"          -> ManoSeleccionScreen(onManoClick = { seccion = it }, onBack = { seccion = "MENU" })
            "MANO_DERECHA"  -> ManoDerechaScreen(onBack = { seccion = "MANO" })
            "MANO_ZURDA"    -> ManoZurdaScreen(onBack = { seccion = "MANO" })
            "VOZ"           -> VozSeleccionScreen(onVozClick = { seccion = it }, onBack = { seccion = "MENU" })
            "VOZ_MASCULINA" -> VozConfirmadaScreen(titulo = "Voz masculina", subtitulo = "Configurado con voz masculina", iconoRes = R.drawable.voice_male_white, onBack = { seccion = "VOZ" })
            "VOZ_FEMENINA"  -> VozConfirmadaScreen(titulo = "Voz femenina", subtitulo = "Configurado con voz femenina", iconoRes = R.drawable.voice_fem_white, onBack = { seccion = "VOZ" })
            "VOZ_VELOCIDAD" -> VelocidadHablaScreen(onBack = { seccion = "VOZ" })
            "TECLAS"        -> ConfigDetalleContent(onBack = { seccion = "MENU" })
            else            -> ConfigMenuContent(onSeccionChange = { seccion = it }, onBack = onBack)
        }
    }
}

@Composable
fun ConfigMenuContent(onSeccionChange: (String) -> Unit, onBack: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(60); visible = true }

    Column(modifier = Modifier.fillMaxSize().background(ColorCrema), horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.height(60.dp))
        Image(painter = painterResource(id = R.drawable.logogloveaf), contentDescription = "Logo", modifier = Modifier.fillMaxWidth(0.85f).height(250.dp), contentScale = ContentScale.Fit)
        Text("¡Dispositivo vinculado!", color = ColorAzulOscuro, fontSize = 18.sp)
        Spacer(modifier = Modifier.weight(1f))
        Box(modifier = Modifier.fillMaxWidth().height(350.dp).background(ColorAzulOscuro, OndaInferiorShape).padding(top = 90.dp), contentAlignment = Alignment.TopCenter) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                val items = listOf("Asignación de teclas" to "TECLAS", "Modo mano" to "MANO", "Cambiar la voz" to "VOZ")
                items.forEachIndexed { index, (texto, destino) ->
                    val alpha by animateFloatAsState(targetValue = if (visible) 1f else 0f, animationSpec = tween(400, delayMillis = index * 80, easing = EaseOut), label = "MenuItemAlpha$index")
                    val offset by animateFloatAsState(targetValue = if (visible) 0f else 18f, animationSpec = tween(400, delayMillis = index * 80, easing = EaseOut), label = "MenuItemOffset$index")
                    Box(modifier = Modifier.alpha(alpha).graphicsLayer(translationY = offset)) {
                        BotonPildora(texto) { onSeccionChange(destino) }
                    }
                }
                TextButton(onClick = onBack) { Text("Volver al inicio", color = Color.White.copy(alpha = 0.5f)) }
            }
        }
    }
}

// ESTA ES TU VERSIÓN ORIGINAL DE ASIGNACIÓN DE TECLAS REINSTALADA
@Composable
fun ConfigDetalleContent(onBack: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(60); visible = true }

    Column(modifier = Modifier.fillMaxSize().background(ColorAzulOscuro)) {
        Box(modifier = Modifier.fillMaxWidth().height(220.dp).background(ColorCrema, OndaSuperiorShape).padding(horizontal = 25.dp, vertical = 50.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.icongloveaf), contentDescription = null, modifier = Modifier.size(60.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Configuración", color = ColorAzulOscuro, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Settings, contentDescription = null, tint = ColorAzulOscuro)
                }
            }
        }
        Column(modifier = Modifier.fillMaxWidth().padding(horizontal = 25.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text("Agrega atajos de palabras!", color = Color.White, fontSize = 16.sp, modifier = Modifier.align(Alignment.Start).padding(bottom = 15.dp))
            val opciones = listOf("Buen día") + List(5) { "Atajos de palabras" }
            opciones.forEachIndexed { index, texto ->
                val alpha by animateFloatAsState(targetValue = if (visible) 1f else 0f, animationSpec = tween(380, delayMillis = index * 70, easing = EaseOut), label = "DetalleAlpha$index")
                val offset by animateFloatAsState(targetValue = if (visible) 0f else 14f, animationSpec = tween(380, delayMillis = index * 70, easing = EaseOut), label = "DetalleOffset$index")
                Box(modifier = Modifier.alpha(alpha).graphicsLayer(translationY = offset)) { BotonPildora(texto) {} }
            }
            TextButton(onClick = onBack, modifier = Modifier.padding(top = 10.dp)) { Text("Atrás", color = Color.White.copy(alpha = 0.7f)) }
        }
    }
}

// PANTALLA DE VELOCIDAD CON LA BARRA (SLIDER)
@Composable
fun VelocidadHablaScreen(onBack: () -> Unit) {
    var sliderPosition by remember { mutableStateOf(1f) }

    Column(
        modifier = Modifier.fillMaxSize().background(ColorAzulOscuro).padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Velocidad de habla", color = Color.White, fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(40.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Lento", color = Color.White.copy(alpha = 0.7f))
            Slider(
                value = sliderPosition,
                onValueChange = { sliderPosition = it },
                valueRange = 0.5f..2.0f,
                modifier = Modifier.weight(1f).padding(horizontal = 16.dp),
                colors = SliderDefaults.colors(thumbColor = ColorCrema, activeTrackColor = ColorCrema)
            )
            Text("Rápido", color = Color.White.copy(alpha = 0.7f))
        }

        Text("Velocidad actual: ${String.format("%.1f", sliderPosition)}x", color = ColorCrema, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(40.dp))
        BotonPildora("Confirmar") { onBack() }
    }
}

// --- RESTO DE COMPONENTES (MANO, VOZ, CARDS) ---

@Composable
fun ManoSeleccionScreen(onManoClick: (String) -> Unit, onBack: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(60); visible = true }

    Column(modifier = Modifier.fillMaxSize().background(ColorAzulOscuro), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth().height(220.dp).background(ColorCrema, OndaSuperiorShape).padding(horizontal = 25.dp, vertical = 50.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.icongloveaf), contentDescription = null, modifier = Modifier.size(60.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Modo mano", color = ColorAzulOscuro, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Settings, contentDescription = null, tint = ColorAzulOscuro)
                }
            }
        }
        Text("Seleccioná tu mano dominante", color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(top = 24.dp, bottom = 32.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            listOf(Triple("Diestra", "MANO_DERECHA", false), Triple("Zurda", "MANO_ZURDA", true)).forEachIndexed { index, (label, destino, espejo) ->
                val alpha by animateFloatAsState(targetValue = if (visible) 1f else 0f, animationSpec = tween(400, delayMillis = index * 100, easing = EaseOut), label = "ManoCardAlpha$index")
                val offset by animateFloatAsState(targetValue = if (visible) 0f else 20f, animationSpec = tween(400, delayMillis = index * 100, easing = EaseOut), label = "ManoCardOffset$index")
                Box(modifier = Modifier.weight(1f).alpha(alpha).graphicsLayer(translationY = offset)) {
                    ManoCard(label = label, espejo = espejo, onClick = { onManoClick(destino) })
                }
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onBack, modifier = Modifier.padding(bottom = 30.dp)) { Text("Atrás", color = Color.White.copy(alpha = 0.7f)) }
    }
}

@Composable
fun ManoCard(label: String, espejo: Boolean, onClick: () -> Unit) {
    var presionado by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(targetValue = if (presionado) 0.96f else 1f, animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy, stiffness = Spring.StiffnessMedium), label = "ManoCardScale")
    Button(
        onClick = { presionado = true; onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = ColorCrema),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().height(160.dp).graphicsLayer(scaleX = scale, scaleY = scale)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = R.drawable.hand), contentDescription = label, modifier = Modifier.size(90.dp).graphicsLayer(scaleX = if (espejo) -1f else 1f))
            Spacer(modifier = Modifier.height(8.dp))
            Text(label, color = ColorAzulOscuro, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable fun ManoDerechaScreen(onBack: () -> Unit) = ManoConfirmadaScreen("Mano diestra", "Configurado para la mano derecha", false, onBack)
@Composable fun ManoZurdaScreen(onBack: () -> Unit) = ManoConfirmadaScreen("Mano zurda", "Configurado para la mano izquierda", true, onBack)

@Composable
fun ManoConfirmadaScreen(titulo: String, subtitulo: String, espejo: Boolean, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(ColorAzulOscuro), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = R.drawable.hand_white), contentDescription = titulo, modifier = Modifier.size(160.dp).graphicsLayer(scaleX = if (espejo) -1f else 1f))
        Text(titulo, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(subtitulo, color = Color.White.copy(alpha = 0.6f), fontSize = 14.sp)
        Spacer(modifier = Modifier.height(48.dp))
        BotonPildora("Confirmar", onBack)
    }
}

@Composable
fun VozSeleccionScreen(onVozClick: (String) -> Unit, onBack: () -> Unit) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { delay(60); visible = true }

    Column(modifier = Modifier.fillMaxSize().background(ColorAzulOscuro), horizontalAlignment = Alignment.CenterHorizontally) {
        Box(modifier = Modifier.fillMaxWidth().height(220.dp).background(ColorCrema, OndaSuperiorShape).padding(horizontal = 25.dp, vertical = 50.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
                Image(painter = painterResource(id = R.drawable.icongloveaf), contentDescription = null, modifier = Modifier.size(60.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text("Cambiar la voz", color = ColorAzulOscuro, fontSize = 20.sp, fontWeight = FontWeight.Medium)
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Settings, contentDescription = null, tint = ColorAzulOscuro)
                }
            }
        }
        Text("Configurá la voz del dispositivo", color = Color.White, fontSize = 16.sp, modifier = Modifier.padding(top = 24.dp, bottom = 32.dp))
        Row(modifier = Modifier.fillMaxWidth().padding(horizontal = 24.dp), horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            listOf(Triple("Masculina", "VOZ_MASCULINA", R.drawable.voice_male), Triple("Femenina", "VOZ_FEMENINA", R.drawable.voice_fem)).forEachIndexed { index, (label, destino, iconoRes) ->
                val alpha by animateFloatAsState(targetValue = if (visible) 1f else 0f, animationSpec = tween(400, delayMillis = index * 100, easing = EaseOut), label = "VozCardAlpha$index")
                val offset by animateFloatAsState(targetValue = if (visible) 0f else 20f, animationSpec = tween(400, delayMillis = index * 100, easing = EaseOut), label = "VozCardOffset$index")
                Box(modifier = Modifier.weight(1f).alpha(alpha).graphicsLayer(translationY = offset)) {
                    VozCard(label = label, iconoRes = iconoRes, onClick = { onVozClick(destino) })
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Box(modifier = Modifier.fillMaxWidth(0.6f)) {
            VozCard(label = "Velocidad", iconoRes = R.drawable.speed, onClick = { onVozClick("VOZ_VELOCIDAD") })
        }
        Spacer(modifier = Modifier.weight(1f))
        TextButton(onClick = onBack, modifier = Modifier.padding(bottom = 30.dp)) { Text("Atrás", color = Color.White.copy(alpha = 0.7f)) }
    }
}

@Composable
fun VozCard(label: String, iconoRes: Int, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = ColorCrema),
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.fillMaxWidth().height(130.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(painter = painterResource(id = iconoRes), contentDescription = label, modifier = Modifier.size(64.dp))
            Spacer(modifier = Modifier.height(8.dp))
            Text(label, color = ColorAzulOscuro, fontSize = 15.sp, fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
fun VozConfirmadaScreen(titulo: String, subtitulo: String, iconoRes: Int, onBack: () -> Unit) {
    Column(modifier = Modifier.fillMaxSize().background(ColorAzulOscuro), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(id = iconoRes), contentDescription = titulo, modifier = Modifier.size(120.dp))
        Text(titulo, color = Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)
        Text(subtitulo, color = Color.White.copy(alpha = 0.7f), fontSize = 16.sp)
        Spacer(modifier = Modifier.height(32.dp))
        BotonPildora("Confirmar", onBack)
    }
}