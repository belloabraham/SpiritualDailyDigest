package screen.permission

import cafe.adriel.voyager.core.screen.Screen
import androidx.compose.runtime.Composable


class PermissionScreen(
    private val permissionUIState: PermissionUIState,
) : IPermissionScreen{

    override var permission: String? = null
    override var nextScreen: Screen? = null
    override var minSDKVersion: Int? = null

    @Composable
    override fun Content() {
    }

}
actual fun getPermissionScreen(permissionUIState: PermissionUIState): IPermissionScreen {
    return PermissionScreen(permissionUIState)
}