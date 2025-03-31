package org.cccsharonparish.core.domain.exception

sealed interface Result<out D, out E : Exception> {
    data class Success<out D, out E : Exception>(val data: D) : Result<D, E>
    data class Error<out D, out E : Exception>(val error: E) : Result<D, E>
    data class Empty<out D, out E : Exception>(val data: D? = null) : Result<D, E>
    data class Loading<out D, out E : Exception>(val data: D? = null) : Result<D, E>
}