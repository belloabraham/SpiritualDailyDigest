package screen.permission


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import org.cccsharonparish.core.resources.Size

interface IPermissionScreen : Screen {
    var nextScreen: Screen?
}

expect fun getPermissionScreen(permissionUIState: PermissionUIState): IPermissionScreen

@OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
@Composable
fun PermissionPage(permissionUIState: PermissionUIState, paddingValues: PaddingValues, onProceed: () -> Unit) {
    val windowSizeClass = calculateWindowSizeClass()
    val mediumSize = Size.medium(windowSizeClass)
    Column(
        modifier = Modifier.fillMaxSize().padding(paddingValues).padding(horizontal = mediumSize).background(
            color = MaterialTheme.colorScheme.background
        ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.height(Size.medium(windowSizeClass)))
            Text(text = permissionUIState.rationale, style = MaterialTheme.typography.bodyLarge)
        }
        Button(modifier = Modifier.fillMaxWidth(), onClick = onProceed) {
            Text(text = permissionUIState.proceedText)
        }
    }
}