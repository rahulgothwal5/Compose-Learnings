package com.example.composelazygrid

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composelazygrid.ui.theme.ComposeLazyGridTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeLazyGridTheme {

                val items by remember {
                    mutableStateOf((0..100).map {
                        Pair(
                            it, Color(
                                Random.nextFloat(),
                                Random.nextFloat(),
                                Random.nextFloat(),
                                1f
                            )
                        )
                    })
                }

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LazyVerticalGrid(columns = GridCells.Fixed(5), content = {
                        items(items.size) {
                            val item = items[it]
                            Box(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .aspectRatio(1f)
                                    .clip(
                                        RoundedCornerShape(5.dp)
                                    )
                                    .background(
                                        item.second
                                    ), contentAlignment = Alignment.Center
                            ){
                                Text("Item ${item.first}")
                            }
                        }

                    })
                }
            }
        }
    }
}


