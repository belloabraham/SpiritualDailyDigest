package org.cccsharonparish.core.common.extensions

import android.app.Activity
import androidx.annotation.StringRes
import org.cccsharonparish.core.common.helpers.utils.Toast

fun Activity.showToast(msg:String, toastLength:Int = Toast.LENGTH_SHORT){
    Toast(this.applicationContext).showToast(msg, toastLength)
}

fun Activity.showToast(@StringRes msg:Int, toastLength:Int = Toast.LENGTH_SHORT){
    Toast(this.applicationContext).showToast(msg, toastLength)
}