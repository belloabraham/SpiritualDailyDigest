package org.cccsharonparish.core.resources.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Size {

    fun MarginPaddingMedium(windowSizeClass: WindowSizeClass): Dp {
      return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded){
          32.dp
      }else{
          16.dp
      }
    }

    fun MarginPaddingSmall(windowSizeClass: WindowSizeClass): Dp {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded){
            16.dp
        }else{
            8.dp
        }
    }

    fun MarginPaddingLarge(windowSizeClass: WindowSizeClass): Dp {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded){
            48.dp
        }else{
            24.dp
        }
    }

    fun MinimumControl(): Dp {
        return 48.dp
    }

    fun ButtonHeigth(): Dp {
        return 40.dp
    }

    fun OnboardingImage(windowSizeClass: WindowSizeClass): Dp {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded){
            500.dp
        }else{
            250.dp
        }
    }
}