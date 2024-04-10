package theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.SideEffect
import android.app.Activity
import androidx.compose.ui.graphics.toArgb


@Composable
actual fun AppTheme(
    systemIsDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        systemIsDarkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current
    val systemBarAlpha = 0.95f
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            val windowsCompat = WindowCompat.getInsetsController(window, view)

            val newSystemBarColor =  if(systemIsDarkTheme){
                windowsCompat.isAppearanceLightStatusBars = false
                darkColorScheme().background.copy(systemBarAlpha).toArgb()
            }else{
                windowsCompat.isAppearanceLightStatusBars = true
                lightColorScheme().background.copy(systemBarAlpha).toArgb()
            }
            window.statusBarColor = newSystemBarColor
            window.navigationBarColor = newSystemBarColor
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}



