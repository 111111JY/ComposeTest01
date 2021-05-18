package com.example.composetest01


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animate
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.FlingBehavior
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.MaterialTheme.typography
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composetest01.ui.theme.ComposeTest01Theme
import com.example.composetest01.ui.theme.ExpandingCard
import com.example.composetest01.ui.theme.Typography
import com.example.composetest01.ui.theme.showToast
import com.google.android.material.appbar.AppBarLayout

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp()
        }
    }
}


@Preview(showBackground = true, name = "Screen preview")
@Composable
fun DefaultPreview() {
    MyApp()
}

@Composable
fun MyApp() {
    ComposeTest01Theme {
        // A surface container using the 'background' color from the theme
        Surface(color = Color.Black, modifier = Modifier.fillMaxHeight()) {
            Column {
                GenerateToolBar()
                NewsStory("JimYoung")
            }
        }
    }
}

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

@Composable
fun NewsStory(name: String) {
    //向可组合项添加内部状态时，如需要在配置更改或中断（如来电）期间保持该状态，使用rememberSaveable 存储该状态
    val modifyName = rememberSaveable {
        mutableStateOf(name)
    }

    var selected by remember {
        mutableStateOf(true)
    }

    var showed by remember {
        mutableStateOf(true)
    }

    val count = remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .verticalScroll(state = rememberScrollState(), enabled = true)
    ) {
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
            color = colors.surface,
            maxLines = 2
        )

        Spacer(Modifier.height(16.dp))

        Divider(color = Color.White)

        Spacer(Modifier.height(16.dp))

        Text(
            text = "This is your first time to study jetpack compose.",
            style = typography.body1,
            color = colors.surface
        )


        Spacer(Modifier.height(16.dp))

        Row(Modifier.align(Alignment.End)) {
            Text(
                text = "May 2021",
                style = typography.subtitle2,
                color = colors.surface
            )
        }

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = modifyName.value,
            onValueChange = { modifyName.value = it },
            label = { Text(text = "Name", color = Color.White) },
            modifier = Modifier
                .fillMaxWidth()
                .background(colors.primary, RoundedCornerShape(5.dp)),
            maxLines = 2,
            textStyle = TextStyle(colors.surface)
        )

        Spacer(Modifier.height(16.dp))

        Button(modifier = Modifier
            .clip(shape = RoundedCornerShape(5.dp))
            .fillMaxWidth(),
            onClick = {
                selected = !selected
            }) {
            Icon(
                painter = painterResource(id = R.drawable.ic_launcher_foreground),
                contentDescription = null,
                Modifier.size(45.dp)
            )
            Text(text = "Change Image RoundCornerShape")
        }

        Spacer(Modifier.height(16.dp))

        Column {
            Button(modifier = Modifier
                .clip(shape = RoundedCornerShape(5.dp))
                .fillMaxWidth(),
                onClick = {
                    showed = !showed
                }) {
                Text(text = "Show Second Image, clicked Times: ${count.value}")
            }

            Spacer(Modifier.height(16.dp))

            ShowSecondImage(show = showed,
                count,
                updateCount = { newCount ->
                    count.value = newCount
                })

            Spacer(Modifier.height(16.dp))

            ExpandingCard(
                "Compose 如何修改和使用内部状态",
                "1.事件：系统调用 onClick 来响应用户点按某个按钮的操作。\n" +
                        "2.更新状态：使用赋值方法在 onClick 监听器中更改了 expanded。\n" +
                        "3.显示状态：ExpandingCard 会重组，因为 expanded 是已更改的 State<Boolean>，并且 ExpandingCard 在 if(expanded) 代码行中读取相应的值。ExpandingCard 随后会根据 expanded 的新值描述屏幕。"
            )
        }
    }
}


@Composable
fun ShowSecondImage(show: Boolean, count: MutableState<Int>, updateCount: (Int) -> Unit) {
    if (show) {
        Column(Modifier.clickable {
            updateCount(count.value + 1)
        }) {
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
    } else {
        count.value = 0
    }
}



