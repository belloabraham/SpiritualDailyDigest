package screen.about

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.call_24px
import spiritualdailydigest.composeapp.generated.resources.email
import spiritualdailydigest.composeapp.generated.resources.facebook
import spiritualdailydigest.composeapp.generated.resources.facebook_url
import spiritualdailydigest.composeapp.generated.resources.instagram
import spiritualdailydigest.composeapp.generated.resources.instagram_url
import spiritualdailydigest.composeapp.generated.resources.mail_24px
import spiritualdailydigest.composeapp.generated.resources.phone
import spiritualdailydigest.composeapp.generated.resources.phone_number
import spiritualdailydigest.composeapp.generated.resources.twitter
import spiritualdailydigest.composeapp.generated.resources.twitter_url
import spiritualdailydigest.composeapp.generated.resources.web_24px
import spiritualdailydigest.composeapp.generated.resources.website
import spiritualdailydigest.composeapp.generated.resources.website_url

interface IContact{
    val label: String
    val url: String
    @OptIn(ExperimentalResourceApi::class)
    val icon: DrawableResource
}

data class ContactUIState @OptIn(ExperimentalResourceApi::class) constructor(
    override val label: String,
    override val url: String,
    override val icon: DrawableResource
):IContact

data class SocialUIState @OptIn(ExperimentalResourceApi::class) constructor(
    override val label: String,
    override val url: String,
    override val icon: DrawableResource
):IContact

@Composable
@OptIn(ExperimentalResourceApi::class)
fun getAListOfSocialContact(): List<SocialUIState> {
    return listOf(
        SocialUIState(
            stringResource(Res.string.facebook),
            url = stringResource(Res.string.facebook_url),
            Res.drawable.facebook
        ),
        SocialUIState(
            stringResource(Res.string.twitter),
            url = stringResource(Res.string.twitter_url),
            Res.drawable.twitter
        ),
        SocialUIState(
            stringResource(Res.string.instagram),
            url = stringResource(Res.string.instagram_url),
            Res.drawable.instagram
        )
    )
}

@Composable
@OptIn(ExperimentalResourceApi::class)
fun getAListOfContacts(): List<ContactUIState> {
    return listOf(
        ContactUIState(
            label =  stringResource(Res.string.phone),
            url = stringResource(Res.string.phone_number),
            Res.drawable.call_24px
        ),
        ContactUIState(
            label =  stringResource(Res.string.email),
            url =  stringResource(Res.string.email),
            Res.drawable.mail_24px
        ),
        ContactUIState(
            stringResource(Res.string.website),
            url = stringResource(Res.string.website_url),
            Res.drawable.web_24px
        ),
    )
}