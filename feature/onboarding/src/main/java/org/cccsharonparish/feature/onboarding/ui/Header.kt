package org.cccsharonparish.feature.onboarding.ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingHeader(
    title: String,
    subTitle: String,
    verticalItemSpace: Dp
) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(verticalItemSpace)
        ) {
            Text(text = title, style = MaterialTheme.typography.h6, fontSize = 24.sp)
            Text(text = subTitle, style = MaterialTheme.typography.subtitle1, fontSize = 18.sp)
        }
    }
}