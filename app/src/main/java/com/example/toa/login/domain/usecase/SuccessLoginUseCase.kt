package com.example.toa.login.domain.usecase

import com.example.toa.login.domain.model.LoginResult

class SuccessLoginUseCase : LoginUseCase {
    override suspend fun login(email: Email, password: Password): LoginResult {
        return LoginResult.Success
    }
}
