/*
 *
 * Created by Bello Abraham on 10/2023
 *
 */

package org.cccsharonparish.feature.onboarding

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import org.jetbrains.compose.resources.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.ExperimentalResourceApi

@OptIn(ExperimentalResourceApi::class)
@Composable
fun OnboardingImage(
    description: String,
    imageDrawableRes: DrawableResource,
    verticalPadding: Dp,
    verticalItemSpace: Dp
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = verticalPadding),
        verticalArrangement = Arrangement.spacedBy(verticalItemSpace)
    ) {
        Image(
            painter = painterResource(resource = imageDrawableRes),
            contentDescription = description,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.width(200.dp)
                .align(Alignment.CenterHorizontally)
        )
        Text(text = description, style = MaterialTheme.typography.bodyMedium, fontSize = 18.sp)
    }
}
