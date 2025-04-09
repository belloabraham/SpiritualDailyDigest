package extension

import UIText
import org.cccsharonparish.core.domain.error.FirestoreError
import spiritualdailydigest.composeapp.generated.resources.Res
import spiritualdailydigest.composeapp.generated.resources.firestore_error_aborted
import spiritualdailydigest.composeapp.generated.resources.firestore_error_already_exists
import spiritualdailydigest.composeapp.generated.resources.firestore_error_cancelled
import spiritualdailydigest.composeapp.generated.resources.firestore_error_data_loss
import spiritualdailydigest.composeapp.generated.resources.firestore_error_deadline_exceeded
import spiritualdailydigest.composeapp.generated.resources.firestore_error_failed_precondition
import spiritualdailydigest.composeapp.generated.resources.firestore_error_internal
import spiritualdailydigest.composeapp.generated.resources.firestore_error_invalid_argument
import spiritualdailydigest.composeapp.generated.resources.firestore_error_not_found
import spiritualdailydigest.composeapp.generated.resources.firestore_error_ok
import spiritualdailydigest.composeapp.generated.resources.firestore_error_out_of_range
import spiritualdailydigest.composeapp.generated.resources.firestore_error_permission_denied
import spiritualdailydigest.composeapp.generated.resources.firestore_error_unauthenticated
import spiritualdailydigest.composeapp.generated.resources.firestore_error_unavailable
import spiritualdailydigest.composeapp.generated.resources.firestore_error_unimplemented
import spiritualdailydigest.composeapp.generated.resources.firestore_error_unknown
import spiritualdailydigest.composeapp.generated.resources.update

fun FirestoreError.asString(): UIText {
    return when (this) {
        FirestoreError.RESOURCE_EXHAUSTED -> UIText.ResourceString(
            Res.string.update
        )
        FirestoreError.INTERNAL -> UIText.ResourceString(
            Res.string.firestore_error_internal
        )
        FirestoreError.UNAVAILABLE -> UIText.ResourceString(
            Res.string.firestore_error_unavailable
        )
        FirestoreError.UNAUTHENTICATED -> UIText.ResourceString(
            Res.string.firestore_error_unauthenticated
        )
        FirestoreError.NOT_FOUND -> UIText.ResourceString(
            Res.string.firestore_error_not_found
        )

        FirestoreError.ABORTED -> UIText.ResourceString(
            Res.string.firestore_error_aborted
        )

        FirestoreError.DATA_LOSS -> UIText.ResourceString(
            Res.string.firestore_error_data_loss
        )

        FirestoreError.UNKNOWN -> UIText.ResourceString(
            Res.string.firestore_error_unknown
        )

        FirestoreError.FAILED_PRECONDITION -> UIText.ResourceString(
            Res.string.firestore_error_failed_precondition
        )

        FirestoreError.CANCELLED -> UIText.ResourceString(
            Res.string.firestore_error_cancelled
        )

        FirestoreError.OUT_OF_RANGE -> UIText.ResourceString(
            Res.string.firestore_error_out_of_range
        )

        FirestoreError.UNIMPLEMENTED -> UIText.ResourceString(
            Res.string.firestore_error_unimplemented
        )

        FirestoreError.ALREADY_EXISTS -> UIText.ResourceString(
            Res.string.firestore_error_already_exists
        )

        FirestoreError.DEADLINE_EXCEEDED -> UIText.ResourceString(
            Res.string.firestore_error_deadline_exceeded
        )

        FirestoreError.PERMISSION_DENIED -> UIText.ResourceString(
            Res.string.firestore_error_permission_denied
        )
        FirestoreError.OK -> UIText.ResourceString(
            Res.string.firestore_error_ok
        )
        FirestoreError.INVALID_ARGUMENT -> UIText.ResourceString(
            Res.string.firestore_error_invalid_argument
        )
    }
}