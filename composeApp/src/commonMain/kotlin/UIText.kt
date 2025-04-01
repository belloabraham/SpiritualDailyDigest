import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed class UIText {
    data class DynamicString(val value: String) : UIText()
    data class ResourceString(
        val id: StringResource,
    ) : UIText()

    @Composable
    fun asString(
        args: Array<Any> = arrayOf()
    ): String {
        return when (this) {
            is DynamicString -> value
            is ResourceString -> stringResource(id, *args)
        }
    }

    @Composable
    fun asString(): String {
        return when (this) {
            is DynamicString -> value
            is ResourceString -> stringResource(id)
        }
    }
}