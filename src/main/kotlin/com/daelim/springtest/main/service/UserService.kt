package com.daelim.springtest.main.service


import com.daelim.*
import com.daelim.springtest.main.api.model.dto.UserDto
import com.daelim.springtest.main.api.model.dto.UserRequestDto
import com.daelim.springtest.main.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun login(userRequestDto: UserRequestDto): Boolean {
        val user = userRepository.findByEmail(userRequestDto.email)
        return user != null && user.password == userRequestDto.password
    }

    fun createUser(userDto: UserDto): Boolean {
        val existingUser = userRepository.findByEmail(userDto.email)
        if (existingUser != null) {
            return false
        }
        userRepository.save(userDto)
        return true
    }
}
