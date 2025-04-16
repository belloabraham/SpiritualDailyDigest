
/*
 *
 * Created by Bello Abraham on 10/2023
 *
 */

package screen.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.back_button
import spiritualdailydigest.composeapp.generated.resources.lets_get_started
import spiritualdailydigest.composeapp.generated.resources.next
import spiritualdailydigest.composeapp.generated.resources.skip

@OptIn(ExperimentalResourceApi::class)
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
                onClick = {
                    onSkip()
                }
            ) {
                Text(
                    text = stringResource(Res.string.skip),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.secondary
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
                        imageVector = Icons.AutoMirrored.Outlined.KeyboardArrowLeft,
                        contentDescription = stringResource(Res.string.back_button),
                        tint = MaterialTheme.colorScheme.secondary
                    )
                }
            }
            if(nextPage == totalPage){
                Button(
                    onClick = {
                        onSkip()
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                    ),
                ) {
                    Text(
                        text = stringResource(Res.string.lets_get_started),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

            if(nextPage < totalPage){
                Button(
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary,
                    ),
                    onClick = {
                        onNext()
                    }
                ) {
                    Text(
                        text = stringResource(Res.string.next),
                        style = MaterialTheme.typography.labelLarge
                    )
                }
            }

        }

    }
}