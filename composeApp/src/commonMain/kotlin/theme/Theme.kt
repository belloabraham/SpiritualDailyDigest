
package theme

import androidx.compose.runtime.Composable

@Composable
expect fun AppTheme(systemIsDarkTheme: Boolean,
                    content: @Composable () -> Unit): Unit
