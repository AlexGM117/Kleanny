package com.creamoslab.kleanny.data.model

import com.google.gson.annotations.SerializedName

data class UsuarioRegistro(
    @SerializedName("fecha") var fecha: String?,
    @SerializedName("Correo") var correo: String?,
    @SerializedName("Contraseña") var password: String?,
    @SerializedName("DNI") var dni: String?,
    @SerializedName("Nombre") var nombre: String?,
    @SerializedName("PrimerApellido") var primerApellido: String?,
    @SerializedName("SegundoApellido") var segundoApellido: String?,
    @SerializedName("Direccion") var direccion: String?,
    @SerializedName("Telefono") var telefono: String?

) {
    constructor() : this(null, null, null, null, null, null, null, null, null)
}