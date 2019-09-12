package com.creamoslab.kleanny.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creamoslab.kleanny.data.model.UsuarioRegistro
import com.creamoslab.kleanny.data.remote.request.SignUpRequest
import com.creamoslab.kleanny.data.remote.response.BaseResponse
import com.creamoslab.kleanny.ui.base.BaseViewModel
import com.creamoslab.kleanny.ui.register.validator.FieldsValidator
import kotlinx.coroutines.launch

class RegistroViewModel : BaseViewModel() {

    //    Para registro de usuario
    val newUser = UsuarioRegistro()

    /**********************************************VALIDACION DE CAMPOS***********************************************/
//    EMAIL
    private val _isEmailValid = MutableLiveData<Boolean>()

    val isEmailValid: LiveData<Boolean>
        get() = _isEmailValid

    var email = ""
        set(value) {
            field = value
            validateEmail()
        }

    //    CONTRASEÑAS IGUALES
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

    //    LOS CAMPOS NO ESTÁN VACÍOS ******************************************
//    Número de documento
    private val _isDocumentNumberValid = MutableLiveData<Boolean>()

    val isDocumentNumberValid: LiveData<Boolean>
        get() = _isDocumentNumberValid

    var documentNumber = ""
        set(value) {
            field = value
            _isDocumentNumberValid.isValid(value)
        }
//    Nombre
    private val _isNameValid = MutableLiveData<Boolean>()

    val isNameValid: LiveData<Boolean>
        get() = _isNameValid

    var name = ""
        set(value) {
            field = value
            _isNameValid.isValid(value)
        }

//    Primer apellido
    private val _isFirstNameValid = MutableLiveData<Boolean>()

    val isFirstNameValid: LiveData<Boolean>
        get() = _isFirstNameValid

    var firstName = ""
        set(value) {
            field = value
            _isFirstNameValid.isValid(value)
        }

//    Segundo apellido
    private val _isSecondNameValid = MutableLiveData<Boolean>()

    val isSecondNameValid: LiveData<Boolean>
        get() = _isSecondNameValid

    var secondName = ""
        set(value) {
            field = value
            _isSecondNameValid.isValid(value)
        }

//    Dirección
    private val _isAddressValid = MutableLiveData<Boolean>()

    val isAddressValid: LiveData<Boolean>
        get() = _isAddressValid

    var address = ""
        set(value) {
            field = value
            _isAddressValid.isValid(value)
        }

    private fun validateEmail() {
        val isValid = FieldsValidator.isValidEmail(email)
        _isEmailValid.postValue(isValid)
    }

    private fun validatePassword() {
        val isValid = FieldsValidator.arePasswordsValid(password1, password2)
        _arePasswordsValid.postValue(isValid)
    }

    /**********************************************VALIDACION DE CAMPOS***********************************************/

    fun requestRegisterUser(): LiveData<BaseResponse<Nothing>> {
        val result = MutableLiveData<BaseResponse<Nothing>>()
        val request = SignUpRequest("Colombia", newUser)
        scope.launch {
            result.postValue(repository.makeRequest(request))
        }
        return result
    }

    private fun MutableLiveData<Boolean>.isValid(text: String) {
        val isValid = FieldsValidator.isTextValid(text)
        this.postValue(isValid)
    }
}