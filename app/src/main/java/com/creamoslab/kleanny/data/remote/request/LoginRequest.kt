package com.creamoslab.kleanny.data.remote.request

import com.creamoslab.kleanny.data.model.UsuarioCredenciales

data class LoginRequest(
    var paisUsuario: String,
    var usuarioCredenciales: UsuarioCredenciales)