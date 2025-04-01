package org.cccsharonparish.core.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateTimeUtil {

    fun date(): LocalDate {
        val now = Clock.System.now()
        val currentDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
        return currentDateTime.date
    }
}