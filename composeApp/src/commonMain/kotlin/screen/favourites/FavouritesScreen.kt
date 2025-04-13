package screen.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.ListItemDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import getNavigationIcon
import org.cccsharonparish.core.resources.Size
import org.cccsharonparish.core.resources.iconColor
import org.cccsharonparish.core.ui.Header
import org.cccsharonparish.core.ui.SwipeToDeleteItem
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.about
import spiritualdailydigest.composeapp.generated.resources.delete_all
import spiritualdailydigest.composeapp.generated.resources.delete_all_favourite_message
import spiritualdailydigest.composeapp.generated.resources.favourites
import spiritualdailydigest.composeapp.generated.resources.no
import spiritualdailydigest.composeapp.generated.resources.notification_time_title
import spiritualdailydigest.composeapp.generated.resources.yes

class FavouritesScreen:Screen {

    @OptIn(ExperimentalMaterial3WindowSizeClassApi::class, ExperimentalResourceApi::class,
        ExperimentalMaterial3Api::class
    )
    @Composable
    override fun Content() {
        val windowSizeClass = calculateWindowSizeClass()
        val navigator = LocalNavigator.current
        val sizeMedium = Size.medium(windowSizeClass)
        var showDeleteAllFavouriteConsentDialog by rememberSaveable { mutableStateOf(false) }


        Scaffold(
            topBar = {
                Header(Res.string.favourites, getNavigationIcon(), actions = {
                    if (true) {  //TODO
                        IconButton(onClick = {
                            showDeleteAllFavouriteConsentDialog = true
                        }) {
                            Icon(
                                vectorResource(Res.drawable.delete_all),
                                contentDescription = "Delete all",
                                tint = iconColor()
                            )
                        }
                    }
                }){
                    navigator?.pop()
                }
            },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background).navigationBarsPadding()
                .statusBarsPadding()
        ) {
            Column(Modifier.padding(it)) {
                HorizontalDivider()

                LazyColumn(Modifier.weight(1f)) {
                    items(100) { index ->
                        val hymn =
                            "Jerih moh Yah mah Jerih moh Yah mah The host of Angels full of joy in heaven The host of Angels, The host of Angels They are praising God with joyful"
                        SwipeToDeleteItem(hymn, onDelete = {

                        }) {
                            ListItem(
                                colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                                headlineContent = {
                                    Text(
                                        text = "$index. $hymn",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                })
                        }
                    }
                }
            }

        }
    }
}

@Composable
fun DeleteAllConsentDialog(showDialog: Boolean, onDeleteAllClick: (Boolean) -> Unit) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = {
                onDeleteAllClick(false)
            },
            title = {
                Text(stringResource(Res.string.delete_all))
            },
            text = {
                Text(
                    stringResource(
                        Res.string.delete_all_favourite_message,
                    )
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        onDeleteAllClick(true)
                    }
                ) {
                    Text(stringResource(Res.string.yes))
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        onDeleteAllClick(false)
                    }
                ) {
                    Text(stringResource(Res.string.no))
                }
            }
        )
    }
}