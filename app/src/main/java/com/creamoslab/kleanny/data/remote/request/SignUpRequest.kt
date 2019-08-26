package com.creamoslab.kleanny.data.remote.request

import com.creamoslab.kleanny.data.model.UsuarioDatos
import com.creamoslab.kleanny.data.model.UsuarioRegistro

data class SignUpRequest (
    var paisUsuario: String,
    var usuarioRegistro: UsuarioRegistro,
    var usuarioDatos: UsuarioDatos
)