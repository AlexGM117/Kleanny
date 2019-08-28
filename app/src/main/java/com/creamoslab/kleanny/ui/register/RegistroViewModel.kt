package com.creamoslab.kleanny.ui.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creamoslab.kleanny.data.model.UsuarioDatos
import com.creamoslab.kleanny.data.model.UsuarioRegistro
import com.creamoslab.kleanny.data.remote.request.SignUpRequest
import com.creamoslab.kleanny.data.remote.response.BaseResponse
import com.creamoslab.kleanny.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class RegistroViewModel : BaseViewModel() {

    val newUser = UsuarioRegistro()
    val newUserData = UsuarioDatos()

    fun requestRegisterUser() : LiveData<BaseResponse<Nothing>> {
        val result = MutableLiveData<BaseResponse<Nothing>>()
        val request = SignUpRequest("Colombia", newUser, newUserData)
        scope.launch {
            result.postValue(repository.makeRequest(request))
        }
        return result
    }
}