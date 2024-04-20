package screen.permission

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.lifecycle.JavaSerializable
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.notification_perm_rationale
import spiritualdailydigest.composeapp.generated.resources.notification_perm_title
import spiritualdailydigest.composeapp.generated.resources.ok
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

data class PermissionUIState(val title: String, val rationale: String, val proceedText: String) :
    JavaSerializable

@OptIn(ExperimentalResourceApi::class)
@Composable
fun getPermissionUIState(): PermissionUIState {
    val title = stringResource(Res.string.notification_perm_title)
    val ok = stringResource(Res.string.ok)
    val rationale = stringResource(Res.string.notification_perm_rationale)
    return PermissionUIState(title=title, proceedText = ok, rationale = rationale)
}