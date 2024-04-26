package screen.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.capitalize
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import getNavigationIcon
import org.cccsharonparish.core.resources.Size
import org.cccsharonparish.core.resources.iconColor
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.looks_good
import spiritualdailydigest.composeapp.generated.resources.notification_time_message
import spiritualdailydigest.composeapp.generated.resources.notification_time_title

open class NotificationTimeScreen : Screen {

    @OptIn(
        ExperimentalMaterial3Api::class, ExperimentalMaterial3WindowSizeClassApi::class,
        ExperimentalResourceApi::class
    )
    @Composable
    override fun Content() {

        val windowSizeClass = calculateWindowSizeClass()
        val navigator = LocalNavigator.current
        val mediumSize = Size.medium(windowSizeClass)
        val timePickerState = rememberTimePickerState()

        Scaffold(
            topBar = {
                TopAppBar(
                    navigationIcon = {
                        IconButton(onClick = {
                            navigator?.pop()
                        }) {
                            Icon(
                                painterResource(getNavigationIcon()),
                                contentDescription = "Back",
                                tint = iconColor()
                            )
                        }
                    },
                    title = {
                        Text(stringResource(Res.string.notification_time_title))
                    }
                )
            },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
        ) {
            Column(
                Modifier.padding(it).padding(horizontal = mediumSize).padding(bottom = mediumSize)
                    .fillMaxHeight().fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TimePicker(modifier = Modifier.padding(top = mediumSize), state =  timePickerState)

                Button(modifier = Modifier.fillMaxWidth(), onClick = {
                    navigator?.pop()
                }) {
                    Text(stringResource(Res.string.looks_good))
                }
            }
        }
    }
}