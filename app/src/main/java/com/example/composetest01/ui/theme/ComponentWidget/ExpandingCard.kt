package com.example.composetest01.ui.theme

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp


/**
 * 扩展卡片 无状态接口
 */
@Composable
fun ExpandingCard(title: String, body: String) {
    var expanded by rememberSaveable { mutableStateOf(false) }
    ExpandingCard(title = title,
        body = body,
        expanded = expanded,
        onExpand = { expanded = true },
        onCollapse = { expanded = false })
}

/**
 * 扩展卡片 有状态接口
 */
@Composable
fun ExpandingCard(
    title: String,
    body: String,
    expanded: Boolean,
    onExpand: () -> Unit,
    onCollapse: () -> Unit
) {
    Card(Modifier.clip(RoundedCornerShape(10.dp))) {
        Column(
            Modifier
                .fillMaxWidth()
                .animateContentSize() // automatically animate size when it changes
                .padding(start = 16.dp, top = 16.dp, end = 16.dp),
            Arrangement.Center
        ) {
            Text(text = title, style = MaterialTheme.typography.h6)

            if (expanded) {
                Spacer(modifier = Modifier.padding(10.dp))

                Text(text = body, Modifier.padding(top = 8.dp))
                IconButton(
                    onClick = onCollapse,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Expand less"
                    )
                }
            } else {
                IconButton(
                    onClick = onExpand,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Expand more"
                    )
                }
            }
        }
    }
}