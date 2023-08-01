package com.sorsix.gopbackend.api

import com.sorsix.gopbackend.api.requests.UserEditRequest
import com.sorsix.gopbackend.model.User
import com.sorsix.gopbackend.repository.UserRepository
import com.sorsix.gopbackend.service.UserService
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UserController(
    val userRepository: UserRepository,
    val userService: UserService
) {

    @GetMapping
    fun showUserDetails(@RequestParam username: String): User? =
        this.userRepository.findByUsername(username)

    @PutMapping("/edit")
    fun editUserDetails(@RequestBody request: UserEditRequest): User = with(request){
        userService.editUserDetails(username, country)
    }


    @GetMapping("/getAllUsers")
    fun getAllUsersSortedByRating(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam(required = false) sortDirection: String?
    ): ResponseEntity<Page<User>> =
        if (sortDirection != null) {
            val sortingCriteria = Sort.by(Sort.Direction.fromString(sortDirection), "rating")
            ResponseEntity.ok(this.userRepository.findAll(PageRequest.of(page, size, sortingCriteria)))
        } else {
            val sortingCriteria = Sort.by(Sort.Direction.DESC, "rating")
            ResponseEntity.ok(this.userRepository.findAll(PageRequest.of(page, size, sortingCriteria)))
        }
}