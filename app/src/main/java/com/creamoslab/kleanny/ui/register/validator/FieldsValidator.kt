package com.creamoslab.kleanny.ui.register.validator

import android.text.TextUtils
import android.util.Patterns

object FieldsValidator {
    fun isValidEmail(target: CharSequence) : Boolean {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches())
    }

    fun arePasswordsValid(password1: String, password2: String) : Boolean{
        return password1.length >= 8 && password2.length >= 8 && password1 == password2
    }
}