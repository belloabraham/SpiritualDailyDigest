package dev.bellab.feature.onboarding.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.cccsharonparish.core.resources.R
import org.cccsharonparish.core.resources.ui.Size


@Composable
fun OnboardingPage(
    windowSizeClass: WindowSizeClass,
    uiState: OnboardingUIState,
    onSkip: () -> Unit, onNext: () -> Unit, onPrev: () -> Unit
) {
    Column(
        Modifier.padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        OnboardingHeader(uiState.title, uiState.subTitle)
        OnboardingImage(
            uiState.description,
            uiState.imageRes,
            windowSizeClass
        )
        OnboardingFooter(
            windowSizeClass, 
            onSkip = onSkip, 
            onNext =  onNext, 
            onPrev = onPrev
        )
//        GetStartedFooter{
//
//        }
    }
}

@Composable
fun GetStartedFooter(onClick: () -> Unit) {
    Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
        Button(
            onClick = {
                onClick()
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = MaterialTheme.colors.secondary,
            ),
            modifier = Modifier
                .fillMaxWidth(.8f)
                .height(Size.ButtonHeigth())
        ) {
            Text(text = stringResource(id = R.string.lets_get_started), fontSize = 16.sp)
        }
    }
}

@Composable
fun OnboardingHeader(
    title:String,
    subTitle:String
) {
    Row(
        Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = title, style = MaterialTheme.typography.h4)
            Text(text = subTitle, style = MaterialTheme.typography.subtitle2)
        }
        Column {
            Text(text = "Dot Bubble")
        }
    }
}

@Composable
fun OnboardingImage(
    description: String,
    @DrawableRes imageRes: Int,
    windowSizeClass: WindowSizeClass
) {
    Column(
        Modifier
            .fillMaxWidth()
            .padding(vertical = Size.MarginPaddingMedium(windowSizeClass)),
        verticalArrangement = Arrangement.spacedBy(Size.MarginPaddingMedium(windowSizeClass))
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = description,
            Modifier
                .width(Size.OnboardingImage(windowSizeClass))
                .height(Size.OnboardingImage(windowSizeClass))
                .align(Alignment.CenterHorizontally)
        )
        Text(text = description, style = MaterialTheme.typography.body1)
    }
}

@Composable
fun OnboardingFooter(
    windowSizeClass: WindowSizeClass,
    onSkip: () -> Unit, onNext: () -> Unit, onPrev: () -> Unit
) {
    Row(
        Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row {
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.secondary
                ),
                onClick = {
                    onSkip()
                }
            ) {
                Text(text = stringResource(id = R.string.skip), style = MaterialTheme.typography.button)
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(Size.MarginPaddingSmall(windowSizeClass))
        ) {

            IconButton(
                onClick = {
                    onPrev()
                },
            ) {
                Icon(
                    imageVector = Icons.Outlined.KeyboardArrowLeft,
                    contentDescription = stringResource(id = R.string.back_button),
                )
            }
            TextButton(
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Transparent,
                    contentColor = MaterialTheme.colors.secondary
                ),
                onClick = {
                    onNext()
                }
            ) {
                Text(text = stringResource(id = R.string.next), 
                    style = MaterialTheme.typography.button
                )
            }
        }

    }
}