package com.daelim.springtest.main.controller

import com.daelim.springtest.*
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
}
