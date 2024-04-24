package com.daelim.springtest.main.controller

import com.daelim.*
import com.daelim.springtest.main.api.model.dto.UserDto
import com.daelim.springtest.main.api.model.dto.UserRequestDto
import com.daelim.springtest.main.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {
    // 생성자 주입을 통해 UserService 인스턴스를 주입받음

    @PostMapping("/login")
    fun login(@RequestBody userRequestDto: UserRequestDto): ResponseEntity<String> {
        // 로그인 API 엔드포인트
        // UserService를 통해 로그인 처리
        val isLoginSuccess = userService.login(userRequestDto)
        // 로그인 성공 시 200 OK 응답, 실패 시 401 Unauthorized 응답
        return if (isLoginSuccess) {
            ResponseEntity.ok("로그인 성공")
        } else {
            ResponseEntity("로그인 실패", HttpStatus.UNAUTHORIZED)
        }
    }

    @PostMapping("/create")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<String> {
        // 회원 가입 API 엔드포인트
        // UserService를 통해 회원 가입 처리
        val isUserCreated = userService.createUser(userDto)
        // 회원 가입 성공 시 200 OK 응답, 실패 시 400 Bad Request 응답
        return if (isUserCreated) {
            ResponseEntity.ok("회원가입 성공")
        } else {
            ResponseEntity("이미 존재하는 이메일입니다.", HttpStatus.BAD_REQUEST)
        }
    }
}
