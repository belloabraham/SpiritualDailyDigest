package theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import org.cccsharonparish.core.resources.DarkColorScheme
import org.cccsharonparish.core.resources.LightColorScheme
import org.cccsharonparish.core.resources.Typography


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


