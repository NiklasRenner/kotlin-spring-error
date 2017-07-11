package com.example.controller

import com.example.model.User
import com.example.service.UserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/user")
class UserController(val userService: UserService) {

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = userService.findById(id)

    @GetMapping
    fun getAll() = userService.findAll()

    @PostMapping
    fun post(@RequestBody user: User) = userService.save(user)

}