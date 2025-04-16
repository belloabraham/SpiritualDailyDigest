/*
 *
 * Created by Bello Abraham on 9/2023
 *
 */

package org.cccsharonparish.core.common.helpers.utils

import android.content.Context
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import es.dmoral.toasty.Toasty
import org.cccsharonparish.core.resources.R


class Toast(
    private val applicationContext: Context,
    @ColorRes private val backgroundColorRes: Int = R.color.primary_color,
    @DrawableRes private val iconRes: Int = R.drawable.small_notification_icon,
    private val useIcon: Boolean = false
) {

    companion object {
        const val LENGTH_LONG = Toasty.LENGTH_LONG
        const val LENGTH_SHORT = Toasty.LENGTH_SHORT
    }

    fun showToast(@StringRes msg: Int, toastLength: Int = LENGTH_SHORT) {
        showToast(applicationContext.getString(msg), toastLength)
    }

    fun showToast(msg: String, toastLength: Int = LENGTH_SHORT) {
        Toasty.custom(
            applicationContext, msg, iconRes, backgroundColorRes, toastLength, useIcon,
            true
        ).show()
    }
}