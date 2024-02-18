package com.example.circularprogressbar_compose

import android.os.Bundle
import android.view.animation.AlphaAnimation
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.circularprogressbar_compose.ui.theme.CircularProgressBarcomposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(modifier = Modifier.fillMaxSize().background(Color.Black), contentAlignment = Alignment.Center) {
                CircularProgressBar(maxValue = 5000, percentage = .5f)
            }
        }
    }
}

@Composable
fun CircularProgressBar(
    modifier: Modifier = Modifier,
    maxValue: Int = 100,
    percentage: Float = 10f,
    radius: Dp = 50.dp,
    color: Color = Color.Green,
    strokeWidth: Dp = 8.dp,
    fontSize: TextUnit = 15.sp,
    animationDuration: Int = 5000,
    animationDelay: Int = 5000,
) {
    var animationPlayed by remember {
        mutableStateOf(false)
    }

    val currentPercent = animateFloatAsState(
        targetValue = if (animationPlayed) percentage else 0f,
        label = "",
        animationSpec = tween(delayMillis = animationDelay, durationMillis = animationDuration)
    )

    LaunchedEffect(key1 = animationPlayed, block = {
        animationPlayed = true
    })

    Box(modifier = modifier.size(radius * 2), contentAlignment = Alignment.Center) {
        Canvas(modifier = Modifier.size(radius * 2), onDraw = {
            drawArc(
                color = color,
                startAngle = -90f,
                sweepAngle = 360 * currentPercent.value,
                useCenter = false,
                style = Stroke(strokeWidth.toPx(), cap = StrokeCap.Butt)
            )
        })
        Text(
            text = (currentPercent.value * maxValue).toInt().toString(),
            fontWeight = FontWeight.Bold,
            fontSize = fontSize,
            color = Color.Red
        )
    }

}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CircularProgressBarcomposeTheme {
        Greeting("Android")
    }
}