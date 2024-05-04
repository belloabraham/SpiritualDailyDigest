package screen.home

import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.interop.LocalUIViewController
import shareText

@Composable
actual fun ShareButton(text:String) {
    val viewController = LocalUIViewController.current
    IconButton(onClick = {
        shareText(text =  listOf(text), viewController)
    }) {
        ShareIcon()
    }
}