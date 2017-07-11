package com.example.service

import com.example.model.Task
import com.example.model.TaskDao
import org.springframework.stereotype.Service

@Service
class TaskService(val taskDao: TaskDao, val userService: UserService) {

    fun findAll(): List<Task> = taskDao.findAll()

    fun findById(id: Long): Task = taskDao.getOne(id)

    fun save(task: Task): Task = taskDao.save(task)

    fun addUser(taskId: Long, userId: Long): Task {
        val task = taskDao.getOne(taskId)
        val user = userService.findById(userId)

        if (task.users.contains(user)) {
            throw RuntimeException("duplicate")
        }

        task.users.add(user)

        return taskDao.save(task)
    }

}