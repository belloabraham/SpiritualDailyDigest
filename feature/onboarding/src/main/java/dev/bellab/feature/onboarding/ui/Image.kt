package dev.bellab.feature.onboarding.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import org.cccsharonparish.core.resources.ui.Size

@Composable
fun OnboardingImage(
    description: String,
    @DrawableRes imageResId: Int,
    windowSizeClass: WindowSizeClass
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = Size.MarginPaddingMedium(windowSizeClass)),
        verticalArrangement = Arrangement.spacedBy(Size.MarginPaddingMedium(windowSizeClass))
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = description,
            Modifier
                .width(Size.OnboardingImage(windowSizeClass))
                .height(Size.OnboardingImage(windowSizeClass))
                .align(Alignment.CenterHorizontally)
        )
        Text(text = description, style = MaterialTheme.typography.body1)
    }
}
