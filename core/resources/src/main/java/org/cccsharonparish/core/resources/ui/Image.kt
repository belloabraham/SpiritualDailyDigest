package org.cccsharonparish.core.resources.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import org.cccsharonparish.core.resources.R

object Image {
    fun Onboarding(windowSizeClass: WindowSizeClass): Int {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded){
            R.drawable.large_notification_icon
        }else{
            R.drawable.large_notification_icon
        }
    }
}