package screen.about

import org.cccsharonparish.core.domain.Config
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.email
import spiritualdailydigest.composeapp.generated.resources.facebook
import spiritualdailydigest.composeapp.generated.resources.instagram
import spiritualdailydigest.composeapp.generated.resources.mail_24px
import spiritualdailydigest.composeapp.generated.resources.twitter
import spiritualdailydigest.composeapp.generated.resources.web_24px
import spiritualdailydigest.composeapp.generated.resources.website

interface IContact{
    val label: StringResource
    val url: String
    val icon: DrawableResource
}

data class ContactUIState (
    override val label: StringResource,
    override val url: String,
    override val icon: DrawableResource
):IContact

data class SocialUIState(
    override val label: StringResource,
    override val url: String,
    override val icon: DrawableResource
):IContact

fun getAListOfSocialContact(): List<SocialUIState> {
    return listOf(
        SocialUIState(
            Res.string.facebook,
            url = Config.FACEBOOK_URL,
            Res.drawable.facebook
        ),
        SocialUIState(
            Res.string.twitter,
            url =  Config.TWITTER_URL,
            Res.drawable.twitter
        ),
        SocialUIState(
            Res.string.instagram,
            url =  Config.INSTAGRAM_URL,
            Res.drawable.instagram
        )
    )
}


fun getAListOfContacts(): List<ContactUIState> {
    return listOf(
        ContactUIState(
            label =  Res.string.email,
            url =   "mailto:${Config.CONTACT_EMAIL}",
            Res.drawable.mail_24px
        ),
        ContactUIState(
            label = Res.string.website,
            url =   Config.APP_WEBSITE,
            Res.drawable.web_24px
        ),
    )
}