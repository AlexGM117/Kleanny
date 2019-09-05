package com.creamoslab.kleanny.data.manager

import com.creamoslab.kleanny.api.KleannyClient
import com.creamoslab.kleanny.data.remote.request.LoginRequest
import com.creamoslab.kleanny.data.remote.request.SignUpRequest
import com.creamoslab.kleanny.data.remote.response.BaseResponse

class ApiRepository : BaseRepository() {
    val genericMessage = "Por el momento el servicio no esta disponible."

    suspend fun makeRequest(request: SignUpRequest) : BaseResponse<Nothing>? {
        return safeApiCall(
            call = {KleannyClient.getInstance().doSignUp(request).await()},
            errorMessage = genericMessage)
    }

    suspend fun makeRequest(request: LoginRequest) : BaseResponse<Nothing>?{
        return safeApiCall(
            call = {KleannyClient.getInstance().login(request).await()},
            errorMessage = genericMessage)
    }

    suspend fun makeRequest() : BaseResponse<Nothing>?{
        return safeApiCall(
            call = {KleannyClient.getInstance().changePass().await()},
            errorMessage = genericMessage
        )
    }
}