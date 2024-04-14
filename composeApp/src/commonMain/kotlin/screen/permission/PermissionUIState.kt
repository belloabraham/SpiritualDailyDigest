package screen.permission

import androidx.compose.runtime.Composable
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.notification_perm_rationale
import spiritualdailydigest.composeapp.generated.resources.notification_perm_title
import spiritualdailydigest.composeapp.generated.resources.ok
import org.cccsharonparish.feature.permission.PermissionUIState
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun GetPermissionUIState(): PermissionUIState {
    val title = stringResource(Res.string.notification_perm_title)
    val ok = stringResource(Res.string.ok)
    val rationale = stringResource(Res.string.notification_perm_rationale)
    return PermissionUIState(title=title, proceedText = ok, rationale = rationale)
}