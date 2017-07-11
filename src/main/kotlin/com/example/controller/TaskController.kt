package com.example.controller

import com.example.model.Task
import com.example.service.TaskService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/task")
class TaskController(val taskService: TaskService) {

    @GetMapping
    fun getAll() = taskService.findAll()

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long) = taskService.findById(id)

    @PostMapping
    fun post(@RequestBody task: Task) = taskService.save(task)

    @PostMapping("/{taskId}/user/{userId}")
    fun addUser(@PathVariable taskId: Long, @PathVariable userId: Long) = taskService.addUser(taskId, userId)

}