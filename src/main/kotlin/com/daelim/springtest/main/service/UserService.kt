package com.daelim.springtest.main.service


import com.daelim.springtest.main.api.model.dto.UserDto
import com.daelim.springtest.main.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    // ...

    fun createUser(userDto: UserDto): Boolean {
        // 이메일 중복 체크
        val existingUser = userRepository.findByEmail(userDto.email)
        if (existingUser != null) {
            return false
        }
        // 사용자 정보 저장
        userRepository.save(userDto)
        return true
    }
}
