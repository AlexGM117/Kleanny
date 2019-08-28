package com.creamoslab.kleanny.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creamoslab.kleanny.data.model.UsuarioDatos
import com.creamoslab.kleanny.data.model.UsuarioRegistro
import com.creamoslab.kleanny.data.remote.request.SignUpRequest
import com.creamoslab.kleanny.ui.base.BaseViewModel
import com.creamoslab.kleanny.ui.register.validator.FieldsValidator
import kotlinx.coroutines.launch

class RegistroViewModel : BaseViewModel() {

    //    Para registro de usuario
    val newUser = UsuarioRegistro()
    val newUserData = UsuarioDatos()

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

    private fun validateEmail() {
        val isValid = FieldsValidator.isValidEmail(email)
        _isEmailValid.postValue(isValid)
    }

    private fun validatePassword() {
        val isValid = FieldsValidator.arePasswordsValid(password1, password2)
        _arePasswordsValid.postValue(isValid)
    }

    /**********************************************VALIDACION DE CAMPOS***********************************************/

    fun testRequest() {
        val usuarioRegistro = UsuarioRegistro()
        usuarioRegistro.fecha = "aas"
        usuarioRegistro.contraseña = "CocoApps123"
        usuarioRegistro.correo = "chrisrm1984@hotmail.com"
        val usuarioDatos = UsuarioDatos()
        usuarioDatos.dni = "12456789"
        usuarioDatos.direccion = "Niños Heroes 11, Santa martha Acatitla, Iztapalapa, Mexico DF"
        usuarioDatos.nombre = "Christian"
        usuarioDatos.primerApellido = "Ramirez"
        usuarioDatos.segundoApellido = "Martinez"
        usuarioDatos.telefono = "5512902842"
        val request = SignUpRequest("Colombia", usuarioRegistro, usuarioDatos)
        scope.launch {
            repository.makeRequest(request)
        }
    }

    fun requestRegisterUser() {
        val request = SignUpRequest("Colombia", newUser, newUserData)
        scope.launch {
            repository.makeRequest(request)
        }
    }
}