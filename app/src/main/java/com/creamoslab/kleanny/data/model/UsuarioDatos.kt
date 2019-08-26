package com.creamoslab.kleanny.data.model

import com.google.gson.annotations.SerializedName

data class UsuarioDatos(
    @SerializedName("DNI") var dni: String?,
    @SerializedName("Nombre") var nombre: String?,
    @SerializedName("PrimerApellido") var primerApellido: String?,
    @SerializedName("SegundoApellido") var segundoApellido: String?,
    @SerializedName("Direccion") var direccion: String?,
    @SerializedName("Telefono") var telefono: String?
) {
    constructor() : this(null, null, null, null, null, null)
}