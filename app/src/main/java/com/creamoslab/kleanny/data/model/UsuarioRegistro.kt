package com.creamoslab.kleanny.data.model

import com.google.gson.annotations.SerializedName

data class UsuarioRegistro(
    @SerializedName("fecha") var fecha: String?,
    @SerializedName("Correo") var correo: String?,
    @SerializedName("Contraseña") var contraseña: String?
) {
    constructor() : this(null, null, null)
}