package movieflux.user

import movieflux.config.localDate
import org.jetbrains.exposed.dao.LongIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.andWhere
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import java.time.LocalDate

data class User(val firstName: String?,
                val lastName: String?,
                val birthDay: LocalDate?) {

    companion object : LongIdTable() {
        val first_name = text("first_name").nullable()
        val last_name = text("last_name").nullable()
        val birth_day = localDate("birth_day").nullable()

        fun findAllBornBefore(birthDay: LocalDate): List<User> {
            return User.selectAll()
                    .andWhere { birth_day lessEq birthDay }
                    .map { it.toUser() }
        }
    }

    fun save() {
        User.insert {
            it[first_name] = firstName
            it[last_name] = lastName
            it[birth_day] = birthDay
        }
    }
}

fun ResultRow.toUser() = User(
        firstName = this[User.first_name],
        lastName = this[User.last_name],
        birthDay = this[User.birth_day]
)