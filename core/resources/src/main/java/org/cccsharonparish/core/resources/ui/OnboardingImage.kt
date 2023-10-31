package org.cccsharonparish.core.resources.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import org.cccsharonparish.core.resources.R

object OnboardingImage {

    fun size(windowSizeClass: WindowSizeClass): Dp {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Compact){
            250.dp
        }else{
            500.dp
        }
    }
    fun appLogo(size: Dp): Int {
        return if(size == 250.dp){
            R.drawable.logo_250
        }else{
            R.drawable.logo_500
        }
    }

    fun multiLingualIcon(size: Dp): Int {
        return if(size == 250.dp){
            R.drawable.multilingual_250
        }else{
            R.drawable.multilingual_500
        }
    }

    fun notificationIcon(size: Dp): Int {
        return if(size == 250.dp){
            R.drawable.alarm_250
        }else{
            R.drawable.alarm_500
        }
    }

    fun bookmarkIcon(size: Dp): Int {
        return if(size == 250.dp){
            R.drawable.bookmark_250
        }else{
            R.drawable.bookmark_500
        }
    }

    fun audioIcon(size: Dp): Int {
        return if(size == 250.dp){
            R.drawable.audio_250
        }else{
            R.drawable.audio_250
        }
    }

    fun dailyDigestIcon(size: Dp): Int {
        return if(size == 250.dp){
            R.drawable.daily_digest_250
        }else{
            R.drawable.daily_digest_250
        }
    }

}