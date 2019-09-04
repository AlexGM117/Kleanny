package com.creamoslab.kleanny.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.media.ExifInterface
import android.net.Uri
import android.os.AsyncTask
import android.os.Build
import android.util.Base64
import com.creamoslab.kleanny.data.manager.KleannyPreferences
import java.io.IOException
import java.io.InputStream

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

        /**
         * Este método es seguro si la imagen seleccionada no está rotada. Aunque puede regresar
         * nulo por cualquier razón
         */
        fun fixBitmap(uri: Uri, bitmap: Bitmap, context: Context): Bitmap? {
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
                return null
            }

            var inputStream: InputStream? = null
            var exifInterface: ExifInterface? = null

            try {
                inputStream = context.contentResolver.openInputStream(uri)
                inputStream?.let { exifInterface = ExifInterface(it) }
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                inputStream?.let {
                    try {
                        inputStream.close()
                    } catch (ignored: IOException) {
                        ignored.printStackTrace()
                    }
                }
            }

            val orientation = exifInterface?.getAttributeInt(
                ExifInterface.TAG_ORIENTATION,
                ExifInterface.ORIENTATION_NORMAL
            )

            return rotateBitmap(bitmap, orientation)
        }

        private fun rotateBitmap(
            bitmap: Bitmap,
            orientation: Int?
        ): Bitmap? {
            var rotation = 0f

            orientation?.let {
                rotation = when (it) {
                    ExifInterface.ORIENTATION_ROTATE_90 -> 270f //girar 90 a la izquierda
                    ExifInterface.ORIENTATION_ROTATE_180 -> 180f //girar 180
                    ExifInterface.ORIENTATION_ROTATE_270 -> 90f //girar 90 a la izquierda
                    else -> 0f
                }
            }

            val matrix = Matrix()
            matrix.postRotate(-rotation)
            return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
        }
    }

    private class AsyncSave : AsyncTask<Bitmap, Void, Void>() {

        override fun doInBackground(vararg bitmapImage: Bitmap): Void? {
            KleannyPreferences().encodedBitmap = BitmapEncoder.encodeBitmap(bitmapImage[0])
            return null
        }
    }
}