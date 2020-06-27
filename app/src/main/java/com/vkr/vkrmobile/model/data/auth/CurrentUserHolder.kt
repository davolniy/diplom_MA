package com.vkr.vkrmobile.model.data.auth

interface CurrentUserHolder {
    var userId: Long
    var userName: String
    var userEmail: String
    var userGender: Boolean?
}
