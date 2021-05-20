package com.example.composetest01.ui.theme.ComponentWidget

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

/**
 * 只能针对列布局，涉及子布局为行布局会出现问题，有待完善
 */
@Composable
fun MyBasicColumn(
    modifier: Modifier = Modifier,
    content: @Composable() () -> Unit
) {
    Layout(
        modifier = modifier,
        content = content
    ) { measurables, constraints ->
        val placeables = measurables.map { measureable ->
            measureable.measure(constraints)
        }

        layout(constraints.maxWidth, constraints.maxHeight) {
            var yPosition = 0
            placeables.forEach { placeable ->
                placeable.placeRelative(x = 0, y = yPosition)
                yPosition += placeable.height
            }
        }
    }
}