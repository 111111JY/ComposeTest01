package com.example.composetest01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest01.ui.theme.ComposeTest01Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeTest01Theme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    NewsStory("JimYoung")
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ComposeTest01Theme {
        NewsStory(name = "JimYoung")
    }
}

@Composable
fun NewsStory(name: String) {
    val modifyName = remember {
        mutableStateOf(name)
    }

    var selected by remember {
        mutableStateOf(true)
    }

    var showed by remember {
        mutableStateOf(true)
    }

    Column(modifier = Modifier.padding(20.dp)) {
        Image(
            painter = painterResource(id = R.drawable.header),
            contentDescription = null,
            modifier = Modifier
                .height(180.dp)
                .fillMaxWidth()
                .clip(
                    shape = RoundedCornerShape(
                        if (selected) 15.dp else 0.dp
                    )
                ),
            contentScale = ContentScale.Crop
        )

        Spacer(Modifier.height(16.dp))

        Text(
            text = "Hi,${modifyName.value}!",
            style = typography.h5,
            maxLines = 2
        )

        Text(text = "This is your first time to study jetpack compose.", style = typography.body1)

        Spacer(Modifier.height(16.dp))

        Text(text = "May 2021", style = typography.body2)

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = modifyName.value,
            onValueChange = { modifyName.value = it },
            label = { Text(text = "Name") },
            modifier = Modifier.fillMaxWidth(),
            maxLines = 2,
        )

        Spacer(Modifier.height(16.dp))

        Button(modifier = Modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .fillMaxWidth(),
            onClick = {
                selected = !selected
            }) {
            Text(text = "Change Image RoundCornerShape")
        }

        Spacer(Modifier.height(16.dp))

        Column{
            Button(modifier = Modifier
                .clip(shape = RoundedCornerShape(5.dp))
                .fillMaxWidth(),
                onClick = {
                    showed = !showed
                }) {
                Text(text = "Show Second Image")
            }

            Spacer(Modifier.height(16.dp))

            ShowSecondImage(show = showed)
        }
    }
}

@Composable
fun ShowSecondImage(show: Boolean) {
    if (show) {
        Column {
            Image(
                painter = painterResource(id = R.drawable.header),
                contentDescription = null,
                modifier = Modifier
                    .height(180.dp)
                    .fillMaxWidth()
                    .clip(
                        shape = RoundedCornerShape(
                            15.dp
                        )
                    ),
                contentScale = ContentScale.Crop
            )
        }
    }
}