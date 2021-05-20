package com.example.composetest01.ui.theme.ComponentWidget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.composetest01.R
import com.example.composetest01.ui.theme.Typography
import com.example.composetest01.ui.theme.showToast

@Preview
@Composable
fun ArtistCard() {
    val padding = 16.dp
    Card(
        backgroundColor = Color.White,
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .clickable { "click action".showToast() }
    ) {
        Column(
            Modifier
                .padding(start = padding, end = padding, bottom = padding)
                .fillMaxWidth()
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "profile",
                    Modifier
                        .padding(top = padding, bottom = padding, end = padding)
                        .size(50.dp)
                        .clip(RoundedCornerShape(25.dp)),
                    contentScale = ContentScale.Crop
                )
                Column {
                    Text(text = "JimYoung", style = Typography.h5, fontWeight = FontWeight.Bold)
                    Text(
                        text = "3 minutes ago",
                        style = Typography.subtitle1,
                        color = Color.Gray
                    )
                }
                ConstraintLayout(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterVertically)
                ) {
                    val (button, text) = createRefs()

                    Button(onClick = { "Fellow success".showToast() },
                        modifier = Modifier
                            .clip(RoundedCornerShape(20.dp))
                            .constrainAs(button) {
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                            }) {
                        Text(text = "Fellow")
                    }

                    Text(
                        text = "1,234 fellows", Modifier.constrainAs(text) {
                            top.linkTo(button.bottom, 3.dp)
                            end.linkTo(button.end)
                        }, style = Typography.subtitle1,
                        color = Color.Gray, fontSize = 13.sp
                    )
                }
            }

            Image(
                painter = painterResource(id = R.drawable.artist_pic),
                contentDescription = "artist_pic",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp))
            )
        }
    }

}