package kotlinBackend.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import kotlinBackend.domain.PlayerRepository

@RestController
class PlayerController @Autowired constructor(val repository:PlayerRepository) {

    @RequestMapping("/")
    fun findAll() = repository.findAll()

    @RequestMapping("/{username}")
    fun findByUsername(@PathVariable username: String)
            = repository.findByUsername(username)
}