package kotlinBackend

import com.fasterxml.jackson.module.kotlin.KotlinModule
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.Bean
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder

import kotlinBackend.domain.Player
import kotlinBackend.domain.PlayerRepository

@SpringBootApplication
open class Application {

    private val log = LoggerFactory.getLogger(Application::class.java)

    @Bean
    open fun objectMapperBuilder(): Jackson2ObjectMapperBuilder
            = Jackson2ObjectMapperBuilder().modulesToInstall(KotlinModule())

    @Bean
    open fun init(repository: PlayerRepository) = CommandLineRunner {
        // save a couple of Players
        repository.save(Player("mksoft", "mk"))
        repository.save(Player("ezeDeLosPibes", "Eze"))
        repository.save(Player("gonzaMas10", "Gonza"))

        // fetch all Players
        log.info("Players found with findAll():")
        log.info("-------------------------------")
        for (player in repository.findAll()) {
            log.info(player.toString())
        }
        log.info("")

        // fetch an individual Player by ID
        val Player = repository.findOne(1L)
        log.info("Player found with findOne(1L):")
        log.info("--------------------------------")
        log.info(Player.toString())
        log.info("")

        // fetch Players by last name
        log.info("Player found with findByUsername('gonzaMas10'):")
        log.info("--------------------------------------------")
        for (gonzaMas10 in repository.findByUsername("gonzaMas10")) {
            log.info(gonzaMas10.toString())
        }
        log.info("")
    }

}

fun main(args: Array<String>) {
    SpringApplication.run(Application::class.java, *args)
}