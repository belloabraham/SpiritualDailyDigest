package org.cccsharonparish.core.common.google

import android.app.Activity
import android.content.Context
import com.google.android.play.core.review.ReviewManagerFactory
import org.cccsharonparish.core.common.helpers.logging.Logger

class AppReview (applicationContext: Context) {
    private val reviewManager = ReviewManagerFactory.create(applicationContext)


    /**
     * Request to show an optional review sheet for the app.
     * Review sheet will only show based on app review quota by Google
     *
     * @param activity The current activity where the review sheet will be shown.
     */
    fun showOptionalReviewBottomSheet(activity: Activity){
        reviewManager.requestReviewFlow().addOnCompleteListener {reviewTask->
            if (reviewTask.isSuccessful){
                reviewManager.launchReviewFlow(activity, reviewTask.result)
            }
            reviewTask.exception?.let {
                Logger.logError(it)
            }

        }
    }
}