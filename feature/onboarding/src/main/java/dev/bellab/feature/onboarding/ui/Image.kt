package dev.bellab.feature.onboarding.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
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
        verticalArrangement = Arrangement.spacedBy(Size.MarginPaddingXlarge(windowSizeClass))
    ) {
        Image(
            painter =  painterResource(id = imageResId),
            contentDescription = description,
            contentScale = ContentScale.FillWidth,
           modifier = Modifier
                .fillMaxWidth(.6f)
                .align(Alignment.CenterHorizontally)
        )
        Text(text = description, style = MaterialTheme.typography.body1, fontSize = 18.sp)
    }
}
