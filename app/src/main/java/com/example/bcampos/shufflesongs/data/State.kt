package com.example.bcampos.shufflesongs.data

data class State<T>(val name: Name, val value: T? = null) {
    enum class Name {
        IDLE,
        LOADING,
        LOADED,
        ERROR
    }
}
