package com.example.musicknobcompose

import android.health.connect.datatypes.units.Volume
import android.os.Bundle
import android.view.MotionEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.boundsInWindow
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.musicknobcompose.ui.theme.MusicKnobComposeTheme
import kotlin.math.PI
import kotlin.math.atan
import kotlin.math.atan2
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Box(
                modifier = Modifier
                    .background(Color.Black)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            width = 2.dp, color = Color.Green, shape = RoundedCornerShape(10.dp)
                        )
                        .padding(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    var volume by remember {
                        mutableStateOf(0f)
                    }

                    val barCount = 30

                    VolumeKnob(modifier = Modifier.size(100.dp), onVolumeChange = {
                        volume = it
                    })
                    Spacer(modifier = Modifier.width(20.dp))

                    VolumeBar(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        barCount = barCount,
                        activeBars = (barCount * volume).roundToInt()
                    )

                }
            }
        }
    }
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun VolumeKnob(
    modifier: Modifier = Modifier,
    limitAngle: Float = 25f,
    onVolumeChange: (Float) -> Unit
) {
    Box(modifier = modifier, contentAlignment = Alignment.Center) {
        var rotation by remember {
            mutableStateOf(limitAngle)
        }
        var centerX by remember {
            mutableStateOf(0f)
        }
        var centerY by remember {
            mutableStateOf(0f)
        }
        var touchX by remember {
            mutableStateOf(0f)
        }
        var touchY by remember {
            mutableStateOf(0f)
        }

        Image(painter = painterResource(id = R.drawable.music_knob),
            contentDescription = "music knob",
            modifier = modifier
                .fillMaxSize()
                .onGloballyPositioned {
                    val windowBounds = it.boundsInWindow()
                    centerX = windowBounds.size.width / 2f
                    centerY = windowBounds.size.height / 2f
                }
                .pointerInteropFilter { motionEvent ->
                    touchX = motionEvent.x
                    touchY = motionEvent.y

                    val angle = -atan2(centerX - touchX, centerY - touchY) * (180 / PI).toFloat()

                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                            if (angle !in -limitAngle..limitAngle) {
                                val fixedAngle = if (angle in -180f..limitAngle) {
                                    360f + angle
                                } else {
                                    angle
                                }
                                rotation = fixedAngle
                                val percentage = (fixedAngle - limitAngle) / (360f - 2 * limitAngle)
                                onVolumeChange(percentage)
                                true
                            } else false


                        }

                        else -> false
                    }

                }
                .rotate(rotation)
        )
    }

}

@Composable
fun VolumeBar(modifier: Modifier = Modifier, barCount: Int = 25, activeBars: Int = 5) {
    BoxWithConstraints(
        contentAlignment = Alignment.Center, modifier = modifier
    ) {
        val barWidth = remember {
            constraints.maxWidth / (2f * barCount)
        }

        Canvas(modifier = modifier, onDraw = {
            for (i in 0 until barCount) {
                drawRoundRect(
                    color = if (i in 0..activeBars) Color.Green else Color.Gray,
                    cornerRadius = CornerRadius.Zero,
                    size = Size(barWidth, constraints.maxHeight.toFloat()),
                    topLeft = Offset(i * barWidth * 2f + barWidth / 2f, 0f)
                )
            }
        })
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MusicKnobComposeTheme {
        Greeting("Android")
    }
}