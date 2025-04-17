package screen.home

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.chevron_right_24px
import spiritualdailydigest.composeapp.generated.resources.favorite_24px
import spiritualdailydigest.composeapp.generated.resources.favourite_list
import spiritualdailydigest.composeapp.generated.resources.list_24px
import spiritualdailydigest.composeapp.generated.resources.more_options

data class NavigationItem(
    val leadingIconRes: DrawableResource,
    val trailingIconRes: DrawableResource,
    val headline: String
)

@Composable
fun getNavigationItems(): List<NavigationItem> {
    return listOf(
        NavigationItem(
            leadingIconRes = Res.drawable.favorite_24px,
            trailingIconRes = Res.drawable.chevron_right_24px,
            headline = stringResource(Res.string.favourite_list)
        ),
        NavigationItem(
            leadingIconRes = Res.drawable.list_24px,
            trailingIconRes = Res.drawable.chevron_right_24px,
            headline = stringResource(Res.string.more_options)
        ),
    )
}

