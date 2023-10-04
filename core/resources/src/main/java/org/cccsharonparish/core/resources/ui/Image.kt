package org.cccsharonparish.core.resources.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import org.cccsharonparish.core.resources.R

object Image {
    fun onboardingAppLogo(windowSizeClass: WindowSizeClass): Int {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
            R.drawable.logo_250
        }else{
            R.drawable.logo_500
        }
    }

    fun onboardingMultiLingualIcon(windowSizeClass: WindowSizeClass): Int {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
            R.drawable.multilingual_250
        }else{
            R.drawable.multilingual_500
        }
    }

    fun onboardingNotificationIcon(windowSizeClass: WindowSizeClass): Int {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
            R.drawable.alarm_250
        }else{
            R.drawable.alarm_500
        }
    }

    fun onboardingBookmarkIcon(windowSizeClass: WindowSizeClass): Int {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
            R.drawable.bookmark_250
        }else{
            R.drawable.bookmark_500
        }
    }

    fun onboardingAudioIcon(windowSizeClass: WindowSizeClass): Int {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
            R.drawable.audio_250
        }else{
            R.drawable.audio_250
        }
    }

}