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

    @PostMapping("/login")
    fun login(@RequestBody userRequestDto: UserRequestDto): ResponseEntity<String> {
        val isLoginSuccess = userService.login(userRequestDto)
        return if (isLoginSuccess) {
            ResponseEntity.ok("로그인 성공")
        } else {
            ResponseEntity("로그인 실패", HttpStatus.UNAUTHORIZED)
        }
    }

    @PostMapping("/create")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<String> {
        val isUserCreated = userService.createUser(userDto)
        return if (isUserCreated) {
            ResponseEntity.ok("회원가입 성공")
        } else {
            ResponseEntity("이미 존재하는 이메일입니다.", HttpStatus.BAD_REQUEST)
        }
    }
}
