package screen.permission

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.cccsharonparish.core.common.helpers.notification.Notification

actual fun getPermissionScreen(permissionUIState: PermissionUIState): IPermissionScreen {
    return PermissionScreen(permissionUIState)
}

class PermissionScreen(
    private val permissionUIState: PermissionUIState,
) : IPermissionScreen {
    override var nextScreen: Screen? = null

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            permissionUIState.title,
                            style = MaterialTheme.typography.headlineMedium
                        )
                    },
                )
            },
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
                .navigationBarsPadding().statusBarsPadding()
        ) {
            PermissionPage(permissionUIState, it) {
                Notification.requestNotificationPermission {
                    navigator?.replace(nextScreen!!)
                }
            }
        }
    }
}
