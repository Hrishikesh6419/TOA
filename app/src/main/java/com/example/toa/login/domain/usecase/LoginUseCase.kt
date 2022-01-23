package com.example.toa.login.domain.usecase

import com.example.toa.login.domain.model.LoginResult

@Suppress("UnusedPrivateMember")
@JvmInline
value class Email(private val email: String)

@Suppress("UnusedPrivateMember")
@JvmInline
value class Password(private val password: String)

interface LoginUseCase {
    suspend fun login(
        email: Email,
        password: Password
    ): LoginResult
}
