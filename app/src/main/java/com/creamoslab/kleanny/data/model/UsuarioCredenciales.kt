package com.creamoslab.kleanny.data.model

class UsuarioCredenciales(
    var correoelectronico: String?,
    var usuariopassword: String?,
    var usuariopasswordActual: String?,
    var usuariopasswordNuevo: String?
) {
    constructor() : this(null, null, null, null)
}