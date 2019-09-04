package com.creamoslab.kleanny.data.manager

import android.content.Context
import com.creamoslab.kleanny.KleannyApplication

class KleannyPreferences {
    companion object {
        private val TAG = KleannyPreferences::class.java.simpleName

        private val sharedPreferences = KleannyApplication.applicationContext()
                .getSharedPreferences("com.creamoslab.kleanny.prefs", Context.MODE_PRIVATE)

        private val KEY_ENCODE_AVATAR = "$TAG.KEY_ACCESS_TOKEN"
        private val KEY_EMAIL = "$TAG.KEY_EMAIL"
        private val KEY_SHOW_TUTORIAL = "$TAG.KEY_SHOW_TUTORIAL"
    }

    var encodedBitmap: String?
        get() = sharedPreferences.getString(KEY_ENCODE_AVATAR, "")
        set(encoded) = sharedPreferences.edit().putString(KEY_ENCODE_AVATAR, encoded).apply()

    var email: String?
        get() = sharedPreferences.getString(KEY_EMAIL, "")
        set(userEmail) = sharedPreferences.edit().putString(KEY_EMAIL, userEmail).apply()

    var showTutorial: Boolean
        get() = sharedPreferences.getBoolean(KEY_SHOW_TUTORIAL, true)
        set(showTutorial) = sharedPreferences.edit().putBoolean(KEY_SHOW_TUTORIAL, showTutorial).apply()
}