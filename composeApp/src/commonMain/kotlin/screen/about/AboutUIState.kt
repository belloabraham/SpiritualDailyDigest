package screen.about

import org.cccsharonparish.core.data.Constant
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.contact_support
import spiritualdailydigest.composeapp.generated.resources.email
import spiritualdailydigest.composeapp.generated.resources.facebook
import spiritualdailydigest.composeapp.generated.resources.give_feedback
import spiritualdailydigest.composeapp.generated.resources.instagram
import spiritualdailydigest.composeapp.generated.resources.mail_24px
import spiritualdailydigest.composeapp.generated.resources.twitter
import spiritualdailydigest.composeapp.generated.resources.web_24px
import spiritualdailydigest.composeapp.generated.resources.website
import spiritualdailydigest.composeapp.generated.resources.whatsapp

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
            url = Constant.FACEBOOK_URL,
            Res.drawable.facebook
        ),
        SocialUIState(
            Res.string.twitter,
            url =  Constant.TWITTER_URL,
            Res.drawable.twitter
        ),
        SocialUIState(
            Res.string.instagram,
            url =  Constant.INSTAGRAM_URL,
            Res.drawable.instagram
        )
    )
}


fun getAListOfContacts(): List<ContactUIState> {
    return listOf(
        ContactUIState(
            label =  Res.string.email,
            url =   "mailto:${Constant.CONTACT_EMAIL}",
            Res.drawable.mail_24px
        ),
        ContactUIState(
            label = Res.string.website,
            url =   Constant.APP_WEBSITE,
            Res.drawable.web_24px
        ),
        ContactUIState(
            label = Res.string.give_feedback,
            url =   Constant.WHATS_APP_FEEDBACK_URL,
            Res.drawable.whatsapp
        ),
        ContactUIState(
            label = Res.string.contact_support,
            url =   Constant.WHATS_APP_SUPPORT_URL,
            Res.drawable.whatsapp
        ),
    )
}