package screen.about

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import cafe.adriel.voyager.core.model.ScreenModel
import getAppVersion
import org.cccsharonparish.core.common.utils.DateTimeUtil

class AboutScreenModel : ScreenModel {
    val appVersion by derivedStateOf { getAppVersion() }
    val contacts by derivedStateOf { getAListOfContacts() }
    val socialContacts by derivedStateOf { getAListOfSocialContact() }
    val year by derivedStateOf { DateTimeUtil.date().year }

}