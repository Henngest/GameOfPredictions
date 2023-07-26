package com.sorsix.gopbackend.repository

import com.sorsix.gopbackend.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, String> {

    fun findByUsername(username: String): User?
}