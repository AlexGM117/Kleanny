package com.creamoslab.kleanny.data.manager

import android.content.Context
import com.creamoslab.kleanny.KleannyApplication

class KleannyPreferences {
    companion object {
        private val TAG = this::class.java.simpleName

        private val sharedPreferences = KleannyApplication.applicationContext()
                .getSharedPreferences("com.creamoslab.kleanny.prefs", Context.MODE_PRIVATE)

        private val KEY_ACCESS_TOKEN = "$TAG.KEY_ACCESS_TOKEN"
        private val KEY_FIRST_ACCESS = "$TAG.KEY_FIRST_ACCESS"
        private val KEY_SHOW_TUTORIAL = "$TAG.KEY_SHOW_TUTORIAL"
    }

    var encodedBitmap: String?
        get() = sharedPreferences.getString(KEY_ACCESS_TOKEN, "")
        set(encoded) = sharedPreferences.edit().putString(KEY_ACCESS_TOKEN, encoded).apply()

    var firstAccess: Boolean
        get() = sharedPreferences.getBoolean(KEY_FIRST_ACCESS, true)
        set(firstAccess) = sharedPreferences.edit().putBoolean(KEY_FIRST_ACCESS, firstAccess).apply()

    var showTutorial: Boolean
        get() = sharedPreferences.getBoolean(KEY_SHOW_TUTORIAL, true)
        set(showTutorial) = sharedPreferences.edit().putBoolean(KEY_SHOW_TUTORIAL, showTutorial).apply()
}