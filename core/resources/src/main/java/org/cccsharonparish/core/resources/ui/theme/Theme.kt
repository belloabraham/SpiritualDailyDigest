package org.cccsharonparish.core.resources.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Shapes
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.google.accompanist.systemuicontroller.rememberSystemUiController


@Composable
fun SpiritualDailyDigestTheme(
    systemThemeIsDark: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    val systemUiController = rememberSystemUiController()
    val systemThemeIsLight = !systemThemeIsDark

    val themeColors = if (systemThemeIsDark) {
        DarkThemeColours
    } else {
        LightThemeColours
    }

    val shapes = Shapes(
        small = RoundedCornerShape(8.dp),
        medium = RoundedCornerShape(8.dp),
        large = RoundedCornerShape(4.dp)
    )

    MaterialTheme(
        colors = themeColors,
        typography = typography,
        shapes = shapes,
        content = content
    )

    if (systemThemeIsDark) {
        systemUiController.setSystemBarsColor(
            color = darkBackground.copy(alpha = 0.95f),
            darkIcons = false
        )
    }

    if(systemThemeIsLight){
        systemUiController.setSystemBarsColor(
            color = white,
            darkIcons = true
        )
    }
}