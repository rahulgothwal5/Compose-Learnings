package com.example.composequoteapp

import android.support.v4.os.IResultReceiver2.Default
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardElevation
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavOptions
import androidx.navigation.NavOptionsBuilder
import androidx.navigation.Navigator
import com.example.composequoteapp.modal.Quote
import com.example.composequoteapp.ui.TopBar
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun QuoteDetailScreen(
    navigator: DestinationsNavigator,
    quote: String,
    author: String,
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Card {

        }
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            TopBar()
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        Brush.sweepGradient(
                            colors = listOf(
                                Color.LightGray,
                                Color.DarkGray
                            )
                        )
                    ), contentAlignment = Alignment.Center
            ) {

                Card() {
                    Row(
                        Modifier
                            .fillMaxWidth(.8f)
                            .fillMaxHeight(.4f)
                            .background(Color.White)
                    ) {
                        Icon(
                            imageVector = Icons.Filled.FormatQuote,
                            modifier = Modifier
                                .padding(top = 90.dp)
                                .padding(horizontal = 10.dp)
                                .size(50.dp)
                                .background(Color.Black).rotate(180f)
                            , tint = Color.White,
                            contentDescription = "Quote"
                        )
                        Column(
                            Modifier
                                .fillMaxWidth()
                                .fillMaxHeight()
                                .padding(top = 70.dp, start = 5.dp, end =15.dp)
                        ) {
                            Text(text = quote, color = Color.DarkGray, fontSize = 22.sp)
                            Text(text = "- $author", color = Color.Gray, fontSize = 18.sp)
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun QuoteDetailScreenPreview() {
    val dummy = object : DestinationsNavigator {
        override fun clearBackStack(route: String): Boolean {
            TODO("Not yet implemented")
        }

        override fun navigate(
            route: String,
            onlyIfResumed: Boolean,
            navOptions: NavOptions?,
            navigatorExtras: Navigator.Extras?
        ) {
            TODO("Not yet implemented")
        }

        override fun navigate(
            route: String,
            onlyIfResumed: Boolean,
            builder: NavOptionsBuilder.() -> Unit
        ) {
            TODO("Not yet implemented")
        }

        override fun navigateUp(): Boolean {
            TODO("Not yet implemented")
        }

        override fun popBackStack(): Boolean {
            TODO("Not yet implemented")
        }

        override fun popBackStack(route: String, inclusive: Boolean, saveState: Boolean): Boolean {
            TODO("Not yet implemented")
        }

    }
    QuoteDetailScreen(
        dummy, "The only way to do great work is to love what you do.",
        "Steve Jobs"
    )
}