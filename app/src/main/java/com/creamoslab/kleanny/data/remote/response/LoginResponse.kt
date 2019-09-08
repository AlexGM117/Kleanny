package com.creamoslab.kleanny.data.remote.response

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("FechaAlta") val fechaAlta: String,
    @SerializedName("Correo") val correo: String,
    @SerializedName("Contraseña") val contraseña: String,
    @SerializedName("fecha") val fecha: String,
    @SerializedName("FechaBaja") val fechaBaja: String)