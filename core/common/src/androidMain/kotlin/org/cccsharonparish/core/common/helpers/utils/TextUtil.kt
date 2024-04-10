/*
 *
 * Created by Bello Abraham on 9/2023
 *
 */

package org.cccsharonparish.core.common.helpers.utils

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.core.content.ContextCompat

object TextUtil {

    /**
     * Shares text content with other apps via the Android sharing system.
     *
     * @param text The text content to be shared.
     * @param title The title for the sharing dialog.
     * @param context The context in which the sharing operation will be performed.
     * @param bundleOption Optional bundle to provide additional options for starting the activity.
     */
    fun shareText(context: Context, text: String, title: String, bundleOption: Bundle?) {
        val sendIntent = getTextShareIntent(text, title)
        ContextCompat.startActivity(context, sendIntent, bundleOption)
    }

    /**
     * Creates an Intent for sharing text content with other apps.
     *
     * @param text The text content to be shared.
     * @param title The title for the sharing dialog.
     * @return An Intent that can be used to initiate the text sharing operation.
     */
    private fun getTextShareIntent(text: String, title: String): Intent {
        val sendIntent: Intent = Intent().apply {
            action = Intent.ACTION_SEND
            putExtra(Intent.EXTRA_TEXT, text)
            type = "text/plain"
        }
        return Intent.createChooser(sendIntent, title)
    }
}