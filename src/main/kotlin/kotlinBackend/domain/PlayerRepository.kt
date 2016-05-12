package kotlinBackend.domain

import org.springframework.data.repository.CrudRepository

interface PlayerRepository : CrudRepository<Player, Long> {

    fun findByUsername(username: String): Iterable<Player>
}