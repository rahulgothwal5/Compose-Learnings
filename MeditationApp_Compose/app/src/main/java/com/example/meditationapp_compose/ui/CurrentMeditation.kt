package com.example.meditationapp_compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.meditationapp_compose.R
import com.example.meditationapp_compose.ui.theme.ButtonBlue
import com.example.meditationapp_compose.ui.theme.LightRed
import com.example.meditationapp_compose.ui.theme.TextWhite

@Composable
fun CurrentMeditation(color: Color = LightRed) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .padding(15.dp)
            .clip(RoundedCornerShape(10.dp))
            .background(color)
            .padding(horizontal = 15.dp, vertical = 20.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(
                text = "Daily Thoughts",
                style = MaterialTheme.typography.headlineMedium,
            )
            Text(
                text = "Meditation > 3-10 min",
                style = MaterialTheme.typography.bodyLarge,
                color = TextWhite
            )
        }
        Box(modifier = Modifier
            .size(40.dp)
            .clip(CircleShape)
            .background(ButtonBlue)
            .padding(10.dp), contentAlignment = Alignment.Center){
            Icon(painter = painterResource(id = R.drawable.ic_play), contentDescription = "Play", tint = Color.White, modifier = Modifier.size(16.dp))
        }
    }

}