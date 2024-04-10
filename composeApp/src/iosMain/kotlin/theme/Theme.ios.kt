package theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
actual fun AppTheme(
    systemIsDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        systemIsDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}


