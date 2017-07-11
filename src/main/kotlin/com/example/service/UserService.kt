package com.example.service

import com.example.model.User
import com.example.model.UserDao
import org.springframework.stereotype.Service

@Service
class UserService(val userDao: UserDao) {

    fun findAll(): List<User> = userDao.findAll()

    fun findById(id: Long): User = userDao.getOne(id)

    fun save(user: User): User = userDao.save(user)

}