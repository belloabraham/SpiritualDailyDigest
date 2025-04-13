package screen.favourites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import getNavigationIcon
import org.cccsharonparish.core.resources.iconColor
import org.cccsharonparish.core.ui.Header
import org.cccsharonparish.core.ui.SwipeToDeleteItem
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.resources.vectorResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.delete_all
import spiritualdailydigest.composeapp.generated.resources.delete_all_favourite_message
import spiritualdailydigest.composeapp.generated.resources.favourites
import spiritualdailydigest.composeapp.generated.resources.no
import spiritualdailydigest.composeapp.generated.resources.yes

class FavouritesScreen:Screen {

    @OptIn(ExperimentalMaterial3Api::class
    )
    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        val favouriteScreenModel = getScreenModel<FavouriteScreenModel>()
        var showDeleteAllFavouriteConsentDialog by rememberSaveable { mutableStateOf(false) }
        val favouriteContents by favouriteScreenModel.favouriteContents.collectAsState(emptyList())


        Scaffold(
            topBar = {
                Header(Res.string.favourites, getNavigationIcon(), actions = {
                    val thereIsMoreThanOneFavouriteContent = favouriteContents.size > 1
                    if (thereIsMoreThanOneFavouriteContent) {
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
                    itemsIndexed(favouriteContents) { index, favouriteContent ->
                        SwipeToDeleteItem(favouriteContent, onDelete = {content->
                                favouriteScreenModel.deleteFavourite(content.id)
                        }) {
                            ListItem(
                                colors = ListItemDefaults.colors(containerColor = MaterialTheme.colorScheme.surfaceContainerHigh),
                                headlineContent = {
                                    Text(
                                        text = "$index. ${favouriteContent.topic}",
                                        maxLines = 1,
                                        overflow = TextOverflow.Ellipsis
                                    )
                                })
                        }
                    }
                }
            }

            DeleteAllConsentDialog(showDeleteAllFavouriteConsentDialog) { isYes ->
                if (isYes) {
                    favouriteScreenModel.deleteAllFavourites()
                }
                showDeleteAllFavouriteConsentDialog = false
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