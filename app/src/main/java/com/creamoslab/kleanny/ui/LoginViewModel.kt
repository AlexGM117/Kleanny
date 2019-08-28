package com.creamoslab.kleanny.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.creamoslab.kleanny.data.model.UsuarioCredenciales
import com.creamoslab.kleanny.data.remote.request.LoginRequest
import com.creamoslab.kleanny.data.remote.response.BaseResponse
import com.creamoslab.kleanny.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class LoginViewModel : BaseViewModel() {

    fun makeLoginRequest(email: String, pass: String) : LiveData<BaseResponse<Nothing>> {
        val result = MutableLiveData<BaseResponse<Nothing>>()
        val request = LoginRequest("Colombia", UsuarioCredenciales(email, pass))
        scope.launch {
            result.postValue(repository.makeRequest(request))
        }
        return result
    }
}