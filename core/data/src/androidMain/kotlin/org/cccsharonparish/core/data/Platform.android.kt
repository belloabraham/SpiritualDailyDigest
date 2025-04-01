package org.cccsharonparish.core.data

import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds

actual fun getDebugConfigMinimumFetchInterval(): Duration {
    return 1800.seconds
}