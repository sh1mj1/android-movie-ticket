package woowacourse.movie.domain.repository

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import woowacourse.movie.domain.model.Grade
import woowacourse.movie.domain.model.Position
import woowacourse.movie.domain.model.Reservation2
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.domain.model.Seat
import woowacourse.movie.domain.model.Seats
import woowacourse.movie.domain.model.Ticket

class ReservationRepositoryTest {
    private lateinit var repository: ReservationRepository

    @BeforeEach
    fun setUp() {
        repository = FakeReservationRepository()
    }

    @Test
    fun `save reservation`() {
        val reservationId =
            repository.save(
                Screen.NULL,
                Seats(
                    Seat(Position(1, 1), Grade.S),
                    Seat(Position(2, 2), Grade.A),
                ),
            ).getOrThrow()

        assertThat(reservationId).isEqualTo(2)
    }

    @Test
    fun `find reservation by id`() {
        val reservation = repository.findById2(-1).getOrThrow()
        assertThat(reservation).isEqualTo(
            Reservation2(
                id = -1,
                screen = Screen.NULL,
                ticket = Ticket(1),
                seats =
                    Seats(
                        Seat(Position(0, 0), Grade.S),
                    ),
            ),
        )
    }

    @Test
    fun `save reservation and find that reservation`() {
        val seats =
            Seats(
                Seat(Position(1, 1), Grade.S),
                Seat(Position(2, 2), Grade.A),
            )

        val reservationId =
            repository.save(
                Screen.NULL,
                seats,
            ).getOrThrow()

        val reservation = repository.findById2(reservationId).getOrThrow()

        assertThat(reservation).isEqualTo(
            Reservation2(
                id = 2,
                screen = Screen.NULL,
                ticket = Ticket(2),
                seats = seats,
            ),
        )
    }
}
