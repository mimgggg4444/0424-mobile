package com.daelim.springtest.main.controller

import com.daelim.springtest.main.api.model.dto.UserDto
import com.daelim.springtest.main.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user")
class UserController(private val userService: UserService) {

    // ...

    @PostMapping("/create")
    fun createUser(@RequestBody userDto: UserDto): ResponseEntity<String> {
        // 회원가입 처리 로직 호출
        val isUserCreated = userService.createUser(userDto)
        return if (isUserCreated) {
            ResponseEntity.ok("회원가입 성공")
        } else {
            ResponseEntity("이미 존재하는 이메일입니다.", HttpStatus.BAD_REQUEST)
        }
    }
}
