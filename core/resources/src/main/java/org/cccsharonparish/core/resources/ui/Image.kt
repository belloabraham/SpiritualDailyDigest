package org.cccsharonparish.core.resources.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import org.cccsharonparish.core.resources.R

object Image {
    fun OnboardingAppLogo(windowSizeClass: WindowSizeClass): Int {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded){
            R.drawable.logo_500
        }else{
            R.drawable.logo_250
        }
    }
}