package com.example.toa.login.domain.usecase

import com.example.toa.login.domain.model.LoginResult

class SuccessCredentialsCredentialsLoginUseCase : CredentialsLoginUseCase {
    override suspend fun login(email: Email, password: Password): LoginResult {
        return LoginResult.Success
    }
}
