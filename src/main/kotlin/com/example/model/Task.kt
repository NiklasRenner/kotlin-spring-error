package com.example.model

import com.fasterxml.jackson.annotation.JsonManagedReference
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.JoinTable
import javax.persistence.ManyToMany

@Entity
data class Task(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
        @Column var name: String = "",
        @JsonManagedReference
        @JoinTable(name = "task_user", joinColumns = arrayOf(JoinColumn(name = "user_id")), inverseJoinColumns = arrayOf(JoinColumn(name = "task_id")))
        @ManyToMany var users: MutableSet<User> = HashSet()
)

@Repository
interface TaskDao : JpaRepository<Task, Long>