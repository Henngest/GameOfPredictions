package com.sorsix.gopbackend.service

import com.sorsix.gopbackend.model.User

interface UserService {

    fun editUserDetails(username: String, country: String): User
}