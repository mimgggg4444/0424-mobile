package com.daelim.springtest.main.repository

import com.daelim.springtest.main.api.model.dto.UserDto
import org.springframework.stereotype.Repository


@Repository
class UserRepository {
    private val users = mutableListOf<UserDto>()

    fun save(user: UserDto) {
        users.add(user)
    }

    fun findByEmail(email: String): UserDto? {
        return users.find { it.email == email }
    }
}
