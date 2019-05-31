package movieflux

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.Logger
import movieflux.user.User
import org.jetbrains.exposed.spring.SpringTransactionManager
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.exposedLogger
import org.jetbrains.exposed.sql.transactions.transaction
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.transaction.annotation.EnableTransactionManagement
import javax.sql.DataSource

@SpringBootApplication
@EnableTransactionManagement
class Application : CommandLineRunner {

    override fun run(vararg args: String?) {
        transaction { SchemaUtils.create(User) }
    }

    @Bean
    fun transactionManager(dataSource: DataSource) = SpringTransactionManager(dataSource)
}

fun main(args: Array<String>) {
    runApplication<Application>(*args)
    (exposedLogger as Logger).level = Level.DEBUG
}
