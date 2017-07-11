package com.example.model

import com.fasterxml.jackson.annotation.JsonBackReference
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.ManyToMany

@Entity
data class User(
        @Id @GeneratedValue(strategy = GenerationType.AUTO) var id: Long = 0,
        @Column(unique = true) var name: String = "",
        @JsonBackReference
        @ManyToMany(mappedBy = "users") var tasks: MutableSet<Task> = HashSet()
)

@Repository
interface UserDao : JpaRepository<User, Long>