package com.creamoslab.kleanny.ui

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.util.Base64
import com.creamoslab.kleanny.data.manager.KleannyPreferences

class BitmapManager  {

    companion object {
        fun saveImageToPreferences(bitmapImage: Bitmap) {
            val save = AsyncSave()
            save.execute(bitmapImage)
        }

        fun loadImageFromPreferences(): Bitmap? {
            val encodedImage = KleannyPreferences().encodedBitmap
            if (!encodedImage.isNullOrEmpty()) {
                val decodedString: ByteArray = Base64.decode(encodedImage, Base64.DEFAULT)
                return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.size)
            }
            return null
        }
    }

    private class AsyncSave : AsyncTask<Bitmap, Void, Void>() {

        override fun doInBackground(vararg bitmapImage: Bitmap): Void? {
            KleannyPreferences().encodedBitmap = BitmapEncoder.encodeBitmap(bitmapImage[0])
            return null
        }
    }
}