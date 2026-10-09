package com.example.ui.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onSplashFinished: () -> Unit
) {
    val scale = remember { Animatable(0.8f) }
    val alpha = remember { Animatable(0f) }

    LaunchedEffect(Unit) {
        alpha.animateTo(1f, animationSpec = tween(600))
        scale.animateTo(1f, animationSpec = tween(600))
        delay(1200) // Show logo while loading app resources
        onSplashFinished()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .scale(scale.value)
                .alpha(alpha.value)
        ) {
            Image(
                painter = painterResource(id = R.drawable.hurifix_logo_exact_1790919761852),
                contentDescription = "Hurifix Logo",
                modifier = Modifier
                    .size(130.dp)
                    .clip(RoundedCornerShape(24.dp))
            )

            Spacer(Modifier.height(20.dp))

            Text(
                text = "Hurifix",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color(0xFF1E293B)
            )

            Spacer(Modifier.height(6.dp))

            Text(
                text = "Many Problems | One Solution",
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color(0xFFD97706)
            )

            Spacer(Modifier.height(36.dp))

            CircularProgressIndicator(
                modifier = Modifier.size(28.dp),
                color = Color(0xFFF59E0B),
                strokeWidth = 3.dp
            )
        }
    }
}
