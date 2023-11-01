package org.cccsharonparish.core.resources.ui

import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

object Size {


    fun medium(windowSizeClass: WindowSizeClass): Dp {
      return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded){
          32.dp
      }else{
          16.dp
      }
    }

    fun small(windowSizeClass: WindowSizeClass): Dp {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded){
            16.dp
        }else{
            8.dp
        }
    }

    fun large(windowSizeClass: WindowSizeClass): Dp {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded){
            48.dp
        }else{
            24.dp
        }
    }

    fun xLarge(windowSizeClass: WindowSizeClass): Dp {
        return if(windowSizeClass.widthSizeClass == WindowWidthSizeClass.Expanded){
            96.dp
        }else{
            48.dp
        }
    }


}