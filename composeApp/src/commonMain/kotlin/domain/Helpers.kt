package domain

import org.cccsharonparish.core.common.utils.DateTimeUtil


fun getContentIdForToday(): String {
    val localDate = DateTimeUtil.date()
    return "${localDate.dayOfMonth}-${localDate.monthNumber}-${localDate.year}"
}