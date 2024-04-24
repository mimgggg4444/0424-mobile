package com.daelim.springtest.main.repository


import com.daelim.*
import com.daelim.springtest.main.api.model.dto.UserDto
import org.springframework.stereotype.Repository

@Repository
class UserRepository {
    // 메모리에 사용자 정보를 저장하는 리포지토리 (실제로는 데이터베이스를 사용해야 함)
    private val users = mutableListOf<UserDto>()

    fun save(user: UserDto) {
        // 사용자 저장 메서드
        users.add(user)
    }

    fun findByEmail(email: String): UserDto? {
        // 이메일로 사용자 조회 메서드
        return users.find { it.email == email }
    }
}