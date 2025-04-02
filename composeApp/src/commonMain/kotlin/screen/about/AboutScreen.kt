package screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import getNavigationIcon
import openUrl
import org.cccsharonparish.core.resources.Size
import org.cccsharonparish.core.ui.Header
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import screen.options.Option
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.about
import spiritualdailydigest.composeapp.generated.resources.about_acknowledgement
import spiritualdailydigest.composeapp.generated.resources.about_description
import spiritualdailydigest.composeapp.generated.resources.app_name
import spiritualdailydigest.composeapp.generated.resources.contact_us
import spiritualdailydigest.composeapp.generated.resources.copyright
import spiritualdailydigest.composeapp.generated.resources.follow_us_on
import spiritualdailydigest.composeapp.generated.resources.ic_launcher
import spiritualdailydigest.composeapp.generated.resources.version

class AboutScreen:Screen {
    @OptIn(
        ExperimentalMaterial3WindowSizeClassApi::class
    )
    @Composable
    override fun Content() {
        val windowSizeClass = calculateWindowSizeClass()
        val navigator = LocalNavigator.current
        val sizeMedium = Size.medium(windowSizeClass)
        val aboutScreenModel = getScreenModel<AboutScreenModel>()

        Scaffold(
            topBar = {
                Header(Res.string.about, getNavigationIcon()){
                    navigator?.pop()
                }
            },
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background).navigationBarsPadding()
                .statusBarsPadding()
        ) {

            Column(modifier = Modifier.padding(it).fillMaxHeight()) {

                ListItem(leadingContent = {
                    Image(
                        painterResource(Res.drawable.ic_launcher),
                        contentDescription = "Logo"
                    )
                }, supportingContent = {
                    Text(stringResource(Res.string.version, aboutScreenModel.appVersion))
                }, headlineContent = {
                    Text(
                        stringResource(Res.string.app_name),
                        style = MaterialTheme.typography.titleLarge
                    )
                })

                Spacer(Modifier.height(sizeMedium))

                Column(
                    Modifier.padding(bottom = sizeMedium).fillMaxHeight().verticalScroll(
                        rememberScrollState()
                    ),
                    verticalArrangement = Arrangement.SpaceBetween
                ) {
                    Column {
                        Column(Modifier.padding(horizontal = sizeMedium)) {
                            Text(stringResource(Res.string.about_description))

                            Text(
                                stringResource(
                                    Res.string.about_acknowledgement,
                                    stringResource(Res.string.app_name)
                                )
                            )
                        }

                        Spacer(Modifier.height(sizeMedium))


                        ListItem(headlineContent = {
                            Text(
                                stringResource(Res.string.contact_us),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W500
                            )
                        })

                        aboutScreenModel.contacts.forEach { item ->
                            Option(item.icon, item.label) {
                                openUrl(item.url)
                            }
                        }

                        ListItem(headlineContent = {
                            Text(
                                stringResource(Res.string.follow_us_on),
                                fontSize = 18.sp,
                                fontWeight = FontWeight.W500
                            )
                        })

                        aboutScreenModel.socialContacts.forEach{  item ->
                            Option(item.icon, item.label) {
                                openUrl(item.url)
                            }
                        }

                        ListItem(headlineContent = {
                            Text(stringResource(Res.string.copyright, aboutScreenModel.year))
                        })

                    }

                }
            }
        }

    }
}