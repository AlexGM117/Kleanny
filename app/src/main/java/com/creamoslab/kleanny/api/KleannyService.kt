package com.creamoslab.kleanny.api

import com.creamoslab.kleanny.data.remote.request.LoginRequest
import com.creamoslab.kleanny.data.remote.request.SignUpRequest
import com.creamoslab.kleanny.data.remote.response.BaseResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface KleannyService {
    @POST("insertaUsuario")
    fun doSignUp(@Body request: SignUpRequest) : Deferred<Response<BaseResponse<Nothing>>>

    @POST("Login")
    fun login(@Body request: LoginRequest) : Deferred<Response<BaseResponse<Nothing>>>

    @POST("CambiaPassword")
    fun changePass() : Deferred<Response<BaseResponse<Nothing>>>
}