package com.example.composemultiscreensupport

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.composemultiscreensupport.ui.theme.ComposeMultiScreenSupportTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeMultiScreenSupportTheme {
                val windowInfo = rememberWindowInfo()
                Scaffold(
                    topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colorScheme.primary)
                                .padding(12.dp), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Multi Screen Support",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                    }
                ) {
                Surface(
                    modifier = Modifier.fillMaxSize().padding(it),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (windowInfo.screenWidthInfo is WindowInfo.WindowType.Compact){
                        LazyColumn(modifier = Modifier.fillMaxSize()) {
                            items(10) {
                                Text(
                                    text = "Item $it",
                                    fontSize = 25.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            Color.Cyan
                                        )
                                        .padding(16.dp)
                                )
                            }

                            items(10) {
                                Text(
                                    text = "Item $it",
                                    fontSize = 25.sp,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(
                                            Color.Green
                                        )
                                        .padding(16.dp)
                                )
                            }
                        }

                    }else{
                        Row(Modifier.fillMaxWidth()) {
                            LazyColumn(modifier = Modifier.fillMaxSize().weight(1f)) {
                                items(10) {
                                    Text(
                                        text = "Item $it",
                                        fontSize = 25.sp,
                                        modifier = Modifier
                                             .fillMaxWidth()
                                            .background(
                                                Color.Cyan
                                            )
                                            .padding(16.dp)
                                    )
                                }
                            }
                            LazyColumn(modifier = Modifier.fillMaxSize().weight(1f)) {
                                items(10) {
                                    Text(
                                        text = "Item $it",
                                        fontSize = 25.sp,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(
                                                Color.Green
                                            )
                                            .padding(16.dp)
                                    )
                                }
                            }

                        }
                    }
                }
                }
            }
        }
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
    ComposeMultiScreenSupportTheme {
        Greeting("Android")
    }
}