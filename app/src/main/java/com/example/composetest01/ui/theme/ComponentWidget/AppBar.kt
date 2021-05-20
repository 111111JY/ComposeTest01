package com.example.composetest01.ui.theme.ComponentWidget

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.inset
import androidx.compose.ui.graphics.drawscope.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest01.R
import com.example.composetest01.ui.theme.showToast

@Composable
fun GenerateToolBar() {
//    Row(
//        Modifier
//            .background(colors.primary)
//            .height(60.dp)
//            .fillMaxWidth()
//            .padding(start = 20.dp, top = 15.dp, bottom = 10.dp, end = 10.dp),
//        Arrangement.Center
//    ) {
//        Text(
//            text = "Jetpack Compose",
//            color = Color.White,
//            style = typography.h6,
//        )
//    }
    TopAppBar(title = {
        Text(
            text = "Jetpack Compose",
            color = Color.White
        )
    }, navigationIcon = {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground),
            contentDescription = null,
            Modifier
                .clip(RoundedCornerShape(5.dp))
                .clickable { "Title Icon has been clicked.".showToast() })
    })
}

@Preview
@Composable
fun drawCanvas() {
    Canvas(Modifier.size(120.dp)) {
        // Draw grey background, drawRect function is provided by the receiver
        drawRect(color = Color.Green)

        // Inset content by 10 pixels on the left/right sides
        // and 12 by the top/bottom
        inset {
            val quadrantSize = size / 2.0f

            // Draw a rectangle within the inset bounds
            drawRect(
                size = quadrantSize,
                color = Color.Red
            )

            rotate(180.0f) {
                drawRect(size = quadrantSize, color = Color.Blue)
            }
        }
    }
}
