package extension

import UIText
import org.cccsharonparish.core.domain.error.FirestoreError
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.update

fun FirestoreError.asString(): UIText {
    return when (this) {
        FirestoreError.RESOURCE_EXHAUSTED -> UIText.ResourceString(
            Res.string.update
        )
        FirestoreError.INTERNAL -> UIText.ResourceString(
            Res.string.update
        )
        FirestoreError.UNAVAILABLE -> UIText.ResourceString(
            Res.string.update
        )
        FirestoreError.UNAUTHENTICATED -> UIText.ResourceString(
            Res.string.update
        )
        FirestoreError.NOT_FOUND -> UIText.ResourceString(
            Res.string.update
        )

        FirestoreError.ABORTED -> UIText.ResourceString(
            Res.string.update
        )

        FirestoreError.DATA_LOSS -> UIText.ResourceString(
            Res.string.update
        )

        FirestoreError.UNKNOWN -> UIText.ResourceString(
            Res.string.update
        )

        FirestoreError.FAILED_PRECONDITION -> UIText.ResourceString(
            Res.string.update
        )

        FirestoreError.CANCELLED -> UIText.ResourceString(
            Res.string.update
        )

        FirestoreError.OUT_OF_RANGE -> UIText.ResourceString(
            Res.string.update
        )

        FirestoreError.UNIMPLEMENTED -> UIText.ResourceString(
            Res.string.update
        )

        FirestoreError.ALREADY_EXISTS -> UIText.ResourceString(
            Res.string.update
        )

        FirestoreError.DEADLINE_EXCEEDED -> UIText.ResourceString(
            Res.string.update
        )

        FirestoreError.PERMISSION_DENIED -> UIText.ResourceString(
            Res.string.update
        )
        FirestoreError.OK -> UIText.ResourceString(
            Res.string.update
        )
        FirestoreError.INVALID_ARGUMENT -> UIText.ResourceString(
            Res.string.update
        )
    }
}