package com.creamoslab.kleanny.data.manager

import com.creamoslab.kleanny.data.remote.response.BaseResponse
import retrofit2.Response
import java.io.IOException
import java.lang.Exception

open class BaseRepository {
    suspend fun <T> safeApiCall(call: suspend() -> Response<BaseResponse<T>>, errorMessage: String): BaseResponse<T>? {
        val result = safeApiResult(call, errorMessage)
        when (result) {
            is Result.Success -> {
                return result.data
            }

            is Result.Error -> return BaseResponse<T>(false, 1, errorMessage, null)
        }
    }

    private suspend fun <T> safeApiResult(call: suspend() -> Response<BaseResponse<T>>, errorMessage: String) : Result<BaseResponse<T>> {
        try {
            val response = call.invoke()
            if (response.isSuccessful && response.body() != null) {
                return Result.Success(response.body()!!)
            }
        } catch (e: Exception) {
            return Result.Error(e)
        }

        return Result.Error(IOException("Error Occurred during getting safe Api result, Custom ERROR - $errorMessage"))
    }
}