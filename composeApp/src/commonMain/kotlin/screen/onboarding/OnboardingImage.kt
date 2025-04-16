/*
 *
 * Created by Bello Abraham on 10/2023
 *
 */

package screen.onboarding

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.logo_250
import spiritualdailydigest.composeapp.generated.resources.logo_500
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi
import spiritualdailydigest.composeapp.generated.resources.alarm_250
import spiritualdailydigest.composeapp.generated.resources.alarm_500
import spiritualdailydigest.composeapp.generated.resources.audio_250
import spiritualdailydigest.composeapp.generated.resources.bookmark_250
import spiritualdailydigest.composeapp.generated.resources.bookmark_500
import spiritualdailydigest.composeapp.generated.resources.daily_digest_250
import spiritualdailydigest.composeapp.generated.resources.daily_digest_500
import spiritualdailydigest.composeapp.generated.resources.multilingual_250
import spiritualdailydigest.composeapp.generated.resources.multilingual_500


object OnboardingImage {

    @OptIn(ExperimentalResourceApi::class)
    fun appLogo(windowSizeClass: WindowSizeClass): DrawableResource {
        return if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            Res.drawable.logo_250
        } else {
            Res.drawable.logo_500
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    fun multiLingual(windowSizeClass: WindowSizeClass): DrawableResource {
        return if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            Res.drawable.multilingual_250
        } else {
            Res.drawable.multilingual_500
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    fun notification(windowSizeClass: WindowSizeClass): DrawableResource {
        return if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            Res.drawable.alarm_250
        } else {
            Res.drawable.alarm_500
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    fun bookmark(windowSizeClass: WindowSizeClass): DrawableResource {
        return if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            Res.drawable.bookmark_250
        } else {
            Res.drawable.bookmark_500
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    fun audio(windowSizeClass: WindowSizeClass): DrawableResource {
        return if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            Res.drawable.audio_250
        } else {
            Res.drawable.audio_250
        }
    }

    @OptIn(ExperimentalResourceApi::class)
    fun dailyDigest(windowSizeClass: WindowSizeClass): DrawableResource {
        return if (windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact) {
            Res.drawable.daily_digest_250
        } else {
            Res.drawable.daily_digest_500
        }
    }
}