package movieflux.ext

import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import java.time.LocalDate

class LocalDateColumnType(nullable: Boolean) : ColumnType(nullable) {
    override fun sqlType() = "DATE"

    override fun valueFromDB(value: Any): Any = when (value) {
        is LocalDate -> value
        is java.sql.Date -> value.toLocalDate()
        is java.sql.Timestamp -> value.toLocalDateTime().toLocalDate()
        is Int -> LocalDate.ofEpochDay(value.toLong())
        is Long -> LocalDate.ofEpochDay(value.toLong())
        else -> throw Exception("unsupported type")
    }
}


fun Table.localDate(name: String): Column<LocalDate> =
        registerColumn(name, LocalDateColumnType(false))