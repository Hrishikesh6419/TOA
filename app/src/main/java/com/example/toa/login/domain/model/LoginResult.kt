package com.example.toa.login.domain.model

sealed class LoginResult {

    object Success : LoginResult()

    sealed class Failure : LoginResult() {

        object InvalidCredentials : LoginResult()

        object Unknown : LoginResult()
    }
}
