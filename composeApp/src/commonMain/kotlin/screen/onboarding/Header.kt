/*
 *
 * Created by Bello Abraham on 10/2023
 *
 */

package screen.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.sp

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
            Text(text = title, style = MaterialTheme.typography.titleLarge, fontSize = 24.sp)
            Text(text = subTitle, style = MaterialTheme.typography.titleMedium, fontSize = 18.sp)
        }
    }
}