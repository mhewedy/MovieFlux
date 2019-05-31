package movieflux.user

import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.toFlux
import java.time.LocalDate

@Transactional
@RestController
@RequestMapping("/users")
class MovieController {

    @PostMapping
    fun save(@RequestBody user: User) {
        user.save()
    }

    @GetMapping
    fun list(): Flux<User> {
        return User
                .findAllBornBefore(LocalDate.now()) // this is still blocking
                .toFlux()
    }
}