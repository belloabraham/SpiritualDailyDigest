package org.cccsharonparish.core.common.helpers.utils

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat

object IconUtil {

    fun getBitmap(context: Context, @DrawableRes res: Int): Bitmap {
        return BitmapFactory.decodeResource(context.resources, res)
    }

    fun getBitmap(drawable: BitmapDrawable): Bitmap {
        return drawable.bitmap
    }

    fun getDrawable(context: Context, bitmap: Bitmap?): Drawable {
        return BitmapDrawable(context.resources, bitmap)
    }

    fun getDrawable(context: Context, @DrawableRes res: Int): Drawable? {
        return ContextCompat.getDrawable(context, res)
    }
}