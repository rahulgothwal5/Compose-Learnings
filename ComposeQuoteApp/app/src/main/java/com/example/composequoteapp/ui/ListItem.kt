package com.example.composequoteapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FormatQuote
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ListItem(
    modifier: Modifier = Modifier,
    quote: String,
    author: String,
) {
    Card(
        Modifier
            .padding(8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.FormatQuote,
                modifier = Modifier.padding(start = 10.dp)
                    .size(40.dp)
                    .background(Color.Black)
                    .rotate(180f), tint = Color.White,
                contentDescription = "Quote"
            )
            Column(
                modifier
                    .fillMaxWidth()

                    .padding(10.dp)
            ) {
                Text(text = quote, color = Color.DarkGray, fontSize = 16.sp)
                Text(text = "- $author", color = Color.Gray, fontSize = 14.sp)
            }
        }

    }

}


@Preview
@Composable
fun ListItemPreview() {
    ListItem(
        Modifier.fillMaxWidth(),
        "The only way to do great work is to love what you do.",
        "Steve Jobs"
    )
}