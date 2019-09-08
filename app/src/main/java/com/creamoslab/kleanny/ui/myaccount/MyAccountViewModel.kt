package com.creamoslab.kleanny.ui.myaccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creamoslab.kleanny.data.model.UsuarioCredenciales
import com.creamoslab.kleanny.data.remote.response.BaseResponse
import com.creamoslab.kleanny.ui.base.BaseViewModel
import com.creamoslab.kleanny.ui.register.validator.FieldsValidator
import kotlinx.coroutines.launch

class MyAccountViewModel: BaseViewModel() {

    private val _arePasswordsValid = MutableLiveData<Boolean>()

    val arePasswordsValid: LiveData<Boolean>
        get() = _arePasswordsValid

    var password1 = ""
        set(value) {
            field = value
            validatePassword()
        }

    var password2 = ""
        set(value) {
            field = value
            validatePassword()
        }

    private fun validatePassword() {
        val isValid = FieldsValidator.arePasswordsValid(password1, password2)
        _arePasswordsValid.postValue(isValid)
    }

    fun callChangePassword(email: String, password: String, newPassword: String) :LiveData<BaseResponse<Nothing>> {
        val result = MutableLiveData<BaseResponse<Nothing>>()
        val usuarioCredenciales = UsuarioCredenciales()
        usuarioCredenciales.correoelectronico = email
        usuarioCredenciales.usuariopasswordActual = password
        usuarioCredenciales.usuariopasswordNuevo = newPassword
        scope.launch {
            result.postValue(repository.makeRequest(usuarioCredenciales))
        }
        return result
    }
}