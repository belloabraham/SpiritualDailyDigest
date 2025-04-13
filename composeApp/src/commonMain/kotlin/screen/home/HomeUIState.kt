package screen.home

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.april
import spiritualdailydigest.composeapp.generated.resources.august
import spiritualdailydigest.composeapp.generated.resources.chevron_right_24px
import spiritualdailydigest.composeapp.generated.resources.december
import spiritualdailydigest.composeapp.generated.resources.favorite_24px
import spiritualdailydigest.composeapp.generated.resources.favourite_list
import spiritualdailydigest.composeapp.generated.resources.february
import spiritualdailydigest.composeapp.generated.resources.january
import spiritualdailydigest.composeapp.generated.resources.july
import spiritualdailydigest.composeapp.generated.resources.june
import spiritualdailydigest.composeapp.generated.resources.list_24px
import spiritualdailydigest.composeapp.generated.resources.march
import spiritualdailydigest.composeapp.generated.resources.may
import spiritualdailydigest.composeapp.generated.resources.more_options
import spiritualdailydigest.composeapp.generated.resources.no
import spiritualdailydigest.composeapp.generated.resources.november
import spiritualdailydigest.composeapp.generated.resources.october
import spiritualdailydigest.composeapp.generated.resources.september

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

fun getMonth(month: Int?): StringResource {
    return when (month) {
        1 ->
            Res.string.january

        2 ->
            Res.string.february

        3 ->
            Res.string.march

        4 ->
            Res.string.april

        5 ->
            Res.string.may

        6 ->
            Res.string.june

        7 ->
            Res.string.july

        8 ->
            Res.string.august

        9 ->
            Res.string.september

        10 ->
            Res.string.october

        11 ->
            Res.string.november

        else ->
            Res.string.december
    }
}
