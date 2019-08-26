package com.creamoslab.kleanny.ui.register

import com.creamoslab.kleanny.data.model.UsuarioDatos
import com.creamoslab.kleanny.data.model.UsuarioRegistro
import com.creamoslab.kleanny.data.remote.request.SignUpRequest
import com.creamoslab.kleanny.ui.base.BaseViewModel
import kotlinx.coroutines.launch

class RegistroViewModel : BaseViewModel() {

    fun testRequest() {
        val usuarioRegistro = UsuarioRegistro()
        usuarioRegistro.fecha = ""
        usuarioRegistro.contraseña = "CocoApps123"
        usuarioRegistro.correo = "chrisrm1984@hotmail.com"
        val usuarioDatos = UsuarioDatos()
        usuarioDatos.dni = "12456789"
        usuarioDatos.direccion = "Niños Heroes 11, Santa martha Acatitla, Iztapalapa, Mexico DF"
        usuarioDatos.nombre = "Christian"
        usuarioDatos.primerApellido = "Ramirez"
        usuarioDatos.segundoApellido = "Martinez"
        usuarioDatos.telefono = "5512902842"
        val request = SignUpRequest("Colombia", usuarioRegistro, usuarioDatos)
        scope.launch {
            repository.makeRequest(request)
        }
    }
}