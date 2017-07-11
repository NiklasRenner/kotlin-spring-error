package com.example

import com.example.model.Task
import com.example.model.User
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpEntity
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ApplicationTest {

    @Autowired
    lateinit var apiClient: TestRestTemplate

    @Test
    fun `multiple test`() {
        // create user
        val user = apiClient.exchange<User>("/user", HttpMethod.POST, User(name = "userName")).apply {
            assert(HttpStatus.OK == statusCode)
        }
        // create task
        val task = apiClient.exchange<Task>("/task", HttpMethod.POST, Task(name = "taskName")).apply {
            assert(HttpStatus.OK == statusCode)
        }

        // this is fine
        val path = "/task/${task.body.id}/user/${user.body.id}"
        val updatedTask = apiClient.exchange<Task>(path, HttpMethod.POST).apply {
            assert(HttpStatus.OK == statusCode)
        }

        // now there is a duplicate -> stack overflow
        apiClient.exchange<Task>(path, HttpMethod.POST)
    }

    // template.exchange(uri, method, body, class) -> template.exchange<class>(uri, method, body)
    private inline fun <reified T> TestRestTemplate.exchange(uri: String, method: HttpMethod = HttpMethod.GET, body: Any = ""): ResponseEntity<T>
            = exchange(uri, method, HttpEntity(body), T::class.java)

}