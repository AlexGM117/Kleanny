package com.creamoslab.kleanny.data.remote.request

import com.creamoslab.kleanny.data.model.UsuarioCredenciales

data class ChangePassRequest(
    var paisUsuario: String,
    var usuarioCredenciales: UsuarioCredenciales)