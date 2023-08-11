package com.sorsix.gopbackend.service.impl

import com.sorsix.gopbackend.model.User
import com.sorsix.gopbackend.model.exceptions.UserDoesNotExistException
import com.sorsix.gopbackend.repository.UserRepository
import com.sorsix.gopbackend.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(private val userRepository: UserRepository) : UserService {
    override fun editUserDetails(username: String, country: String): User {
        val updatedUser = this.userRepository.findByUsername(username)
            ?: throw UserDoesNotExistException("User with username [${username}] does not exist.")
        return this.userRepository.save(
            updatedUser.copy(
                country = country
            )
        )
    }
}