package org.cccsharonparish.core.common.utils

import androidx.compose.ui.text.intl.Locale

object Locale {
    fun getAppLanguageCode(): String {
      return Locale.current.language
    }
}