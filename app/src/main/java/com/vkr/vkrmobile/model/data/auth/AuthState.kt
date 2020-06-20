package ru.feedback.app.model.data.auth

sealed class AuthState

class SignedIn : AuthState()

data class Logout(val authRequired: Boolean) : AuthState()
