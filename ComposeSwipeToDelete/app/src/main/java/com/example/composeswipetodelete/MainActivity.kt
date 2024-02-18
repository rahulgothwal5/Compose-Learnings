package com.example.composeswipetodelete

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeswipetodelete.ui.theme.ComposeSwipeToDeleteTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeSwipeToDeleteTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val list by remember {
                        mutableStateOf(listOf("Apple", "Grapes", "Oranges", "Bananas", "Papaya"))
                    }

                    LazyColumn(modifier = Modifier.fillMaxSize()) {
                        items(list.size,
                            key = {
                                list[it]
                            }) {
                            Text(
                                text = list[it],
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .background(MaterialTheme.colorScheme.background)
                                    .padding(5.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}




