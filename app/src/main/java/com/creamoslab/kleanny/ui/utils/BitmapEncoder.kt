package com.creamoslab.kleanny.ui.utils

import android.graphics.Bitmap
import android.util.Base64
import java.io.ByteArrayOutputStream

object BitmapEncoder {
    fun encodeBitmap(bitmapImage: Bitmap): String? {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray: ByteArray = byteArrayOutputStream.toByteArray()
        return Base64.encodeToString(byteArray, Base64.NO_WRAP)
    }
}