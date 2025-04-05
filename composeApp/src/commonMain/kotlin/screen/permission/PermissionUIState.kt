package screen.permission

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.derivedStateOf
import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import spiritualdailydigest.composeapp.generated.resources.Res
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.notification_perm_rationale
import spiritualdailydigest.composeapp.generated.resources.notification_perm_title
import spiritualdailydigest.composeapp.generated.resources.ok

@Composable
fun getPermissionUIState(permission:String?): State<PermissionUIState> {
    val title = stringResource(Res.string.notification_perm_title)
    val ok = stringResource(Res.string.ok)
    val rationale = stringResource(Res.string.notification_perm_rationale)
    return derivedStateOf {
        PermissionUIState(title = title, proceedText = ok, rationale = rationale,
            permission = permission
        )
    }
}

data class PermissionUIState(val title: String, val rationale: String, val proceedText: String, val permission:String?) :
    JavaSerializable