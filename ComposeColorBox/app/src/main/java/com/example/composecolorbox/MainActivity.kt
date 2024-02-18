package com.example.composecolorbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.composecolorbox.ui.theme.ComposeColorBoxTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeColorBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        val color = remember {
                            mutableStateOf(Color.Yellow)
                        }

                        ColorBox(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                        ) {
                            color.value = it
                        }

                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .weight(1f)
                                .background(color = color.value)
                        )

                    }
                }
            }
        }
    }

}

@Composable
fun ColorBox(
    modifier: Modifier,
    updateColor: (Color) -> Unit
) {
    Box(modifier = modifier
        .background(Color.Yellow)
        .clickable {
            updateColor(
                Color(
                    Random.nextFloat(),
                    Random.nextFloat(),
                    Random.nextFloat(),
                    1f
                )
            )
        })
}
