package screen.permission

import android.annotation.SuppressLint
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import org.cccsharonparish.core.resources.Size

class PermissionScreen(
    private val permissionUIState: PermissionUIState,
    private val permission:String
) : Screen{

    var nextScreen: Screen? = null

    @SuppressLint("NewApi")
    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class)
    @Composable
    override fun Content() {
        val windowSizeClass = calculateWindowSizeClass()
        val navigator = LocalNavigator.current
        val permissionDialog = rememberLauncherForActivityResult(
            contract = ActivityResultContracts.RequestPermission(),
            onResult = {
                navigator?.replace(nextScreen!!)
            }
        )
        Column(
            modifier = Modifier.fillMaxSize().padding(Size.medium(windowSizeClass)),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState())
            ) {
                Text(text = permissionUIState.title, style = MaterialTheme.typography.headlineLarge)
                Spacer(modifier = Modifier.height(Size.medium(windowSizeClass)))
                Text(text = permissionUIState.rationale, style = MaterialTheme.typography.bodyLarge)
            }
            Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    permissionDialog.launch(permission)
            }) {
                Text(text = permissionUIState.proceedText )
            }
        }
    }
}
