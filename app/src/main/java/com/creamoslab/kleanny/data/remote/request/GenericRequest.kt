package com.creamoslab.kleanny.data.remote.request

data class GenericRequest<T>(var t: T) {
    var paisUsuario: String = "Colombia"
}