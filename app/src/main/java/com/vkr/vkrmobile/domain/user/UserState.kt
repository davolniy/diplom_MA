package com.vkr.vkrmobile.domain.user

sealed class UserState

class NoUser : UserState()

data class User(
    val id: Long,
    val name: String,
    val phoneNumber: String,
    val email: String?,
    val gender: Gender
) : UserState()
