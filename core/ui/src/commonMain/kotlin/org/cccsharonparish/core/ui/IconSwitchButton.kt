package org.cccsharonparish.core.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource

@Composable
fun SwitchIconButton(
    isChecked: Boolean,
    checkedIcon: ImageVector,
    unCheckedIcon: ImageVector,
    checkedColor: Color = Color.DarkGray,
    unCheckedColor: Color = Color.DarkGray,
    onClick: () -> Unit
) {
    val icon = if (isChecked) checkedIcon else unCheckedIcon
    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = if (isChecked) checkedColor else unCheckedColor
        )
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
fun SwitchIconButton(
    isChecked: Boolean,
    checkedIcon: DrawableResource,
    unCheckedIcon: DrawableResource,
    checkedColor: Color = Color.DarkGray,
    unCheckedColor: Color = Color.DarkGray,
    onClick: () -> Unit
) {
    val icon = if (isChecked) checkedIcon else unCheckedIcon

    IconButton(
        onClick = onClick,
        modifier = Modifier.size(48.dp)
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = if (isChecked) checkedColor else unCheckedColor
        )
    }
}