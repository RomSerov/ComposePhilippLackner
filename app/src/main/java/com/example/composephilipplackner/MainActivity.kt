package com.example.composephilipplackner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateColor
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.composephilipplackner.ui.theme.ComposePhilippLacknerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePhilippLacknerTheme {

                var sizeState by remember {
                    mutableStateOf(200.dp)
                }
                val size by animateDpAsState(
                    targetValue = sizeState,
                    tween(
                        durationMillis = 3000,
                        delayMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                )

                //для бесконечной анимации
                val infiniteTransition = rememberInfiniteTransition()
                val color by infiniteTransition.animateColor(
                    initialValue = Color.Red,
                    targetValue = Color.Green,
                    animationSpec = infiniteRepeatable(
                        tween(durationMillis = 2000),
                        repeatMode = RepeatMode.Reverse
                    )
                )

                Box(
                    modifier = Modifier
                        .size(size)
                        .background(color),
                    contentAlignment = Alignment.Center
                ) {
                    Button(onClick = { sizeState += 50.dp }) {
                        Text(text = "Увеличить размер")
                    }
                }
            }
        }
    }
}