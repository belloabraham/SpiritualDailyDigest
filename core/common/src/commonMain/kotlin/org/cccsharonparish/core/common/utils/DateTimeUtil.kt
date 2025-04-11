package org.cccsharonparish.core.common.utils

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toLocalDateTime

object DateTimeUtil {

    fun date(): LocalDate {
        val now = Clock.System.now()
        val currentDateTime = now.toLocalDateTime(TimeZone.currentSystemDefault())
        return currentDateTime.date
    }

    fun getLocalDate(day: Int, month: Int, year: Int): LocalDate {
        val inputDate = LocalDate(year, month, day)
        val inputDateTime = inputDate.atStartOfDayIn(TimeZone.UTC)
        val currentZone = TimeZone.currentSystemDefault()
        val localDateTime = inputDateTime.toLocalDateTime(currentZone)
        return localDateTime.date
    }
}