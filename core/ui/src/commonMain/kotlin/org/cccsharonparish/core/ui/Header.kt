package org.cccsharonparish.core.ui

import androidx.compose.runtime.Composable
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import org.cccsharonparish.core.resources.iconColor
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@OptIn(ExperimentalResourceApi::class)
@Composable
fun Header(title: StringResource? = null, navigationIcon: DrawableResource, onBack: () -> Unit) {
    val headerTitle = if(title != null) stringResource(title) else ""
    Header(headerTitle, navigationIcon){
        onBack()
    }
}

@OptIn(ExperimentalResourceApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Header(title: String, navigationIcon: DrawableResource, onBack: () -> Unit) {
    TopAppBar(
        navigationIcon = {
            BackButton(navigationIcon){
                onBack()
            }
        },
        title = {
            Text(title)
        }
    )
}


@OptIn(ExperimentalResourceApi::class)
@Composable
fun BackButton(navigationIcon: DrawableResource, onBack:()->Unit){
    IconButton(onClick = onBack) {
        Icon(
            painterResource(navigationIcon),
            contentDescription = "Back",
            tint = iconColor()
        )
    }
}