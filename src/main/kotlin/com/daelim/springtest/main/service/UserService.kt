package com.daelim.springtest.main.service


import com.daelim.*
import com.daelim.springtest.main.api.model.dto.UserDto
import com.daelim.springtest.main.api.model.dto.UserRequestDto
import com.daelim.springtest.main.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {
    // 생성자 주입을 통해 UserRepository 인스턴스를 주입받음

    fun login(userRequestDto: UserRequestDto): Boolean {
        // 로그인 처리 메서드
        // UserRepository를 통해 이메일로 사용자 조회
        val user = userRepository.findByEmail(userRequestDto.email)
        // 사용자가 존재하고 비밀번호가 일치하면 true, 그렇지 않으면 false 반환
        return user != null && user.password == userRequestDto.password
    }

    fun createUser(userDto: UserDto): Boolean {
        // 회원 가입 처리 메서드
        // UserRepository를 통해 이메일로 사용자 조회
        val existingUser = userRepository.findByEmail(userDto.email)
        // 이미 존재하는 이메일이면 false 반환
        if (existingUser != null) {
            return false
        }
        // 새로운 사용자 저장
        userRepository.save(userDto)
        return true
    }
}