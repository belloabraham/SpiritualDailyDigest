package org.cccsharonparish.feature.onboarding.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.KeyboardArrowLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import org.cccsharonparish.core.resources.R

@Composable
fun OnboardingFooter(
    currentPage:Int,
    nextPage:Int,
    totalPage:Int,
    horizontalItemSpace: Dp,
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
                Text(
                    text = stringResource(id = R.string.skip),
                    style = MaterialTheme.typography.button
                )
            }
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy( horizontalItemSpace)
        ) {

            if (currentPage > 0){
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
            }
            if(nextPage == totalPage){
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = Color.White
                    ),
                    onClick = {
                        onSkip()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.lets_get_started),
                        style = MaterialTheme.typography.button
                    )
                }
            }

            if(nextPage < totalPage){
                Button(
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = MaterialTheme.colors.secondary,
                        contentColor = Color.White
                    ),
                    onClick = {
                        onNext()
                    }
                ) {
                    Text(
                        text = stringResource(id = R.string.next),
                        style = MaterialTheme.typography.button
                    )
                }
            }

        }

    }
}