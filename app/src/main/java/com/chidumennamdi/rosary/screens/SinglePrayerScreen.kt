package com.chidumennamdi.rosary.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBackIos
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SinglePrayerScreen() {
    Column(
        Modifier.background(Color.White)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBackIos,
                contentDescription = "Back"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "Prayer",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Start
            )
        }

        Divider()

        Box(
            Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(color = Color.White)
                .padding(8.dp)
        ) {

            Text("You're trying to lay out a Row with a back icon and a \"Prayer\" title, but there are a few issues:\n" +
                    "\n" +
                    "Modifier.size(20.dp) is shrinking the Text, which might clip it.\n" +
                    "\n" +
                    "You're using Modifier.fillMaxWidth() on the Text, which conflicts with the icon.\n" +
                    "\n" +
                    "The elements are not vertically centered.\n" +
                    "\n" +
                    "There's no space between the icon and the text.")
        }
    }
}


@Preview
@Composable
fun SinglePrayerScreenPreview() {
    SinglePrayerScreen()
}