package dev.bellab.feature.onboarding.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.cccsharonparish.core.resources.ui.Size

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingHeader(
    title: String,
    subTitle: String,
    windowSizeClass: WindowSizeClass
) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(Size.MarginPaddingSmall(windowSizeClass))
        ) {
            Text(text = title, style = MaterialTheme.typography.h6, fontSize = 24.sp)
            Text(text = subTitle, style = MaterialTheme.typography.subtitle1, fontSize = 18.sp)
        }
    }
}