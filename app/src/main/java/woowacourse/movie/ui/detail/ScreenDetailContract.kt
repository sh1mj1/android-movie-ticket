package woowacourse.movie.ui.detail

import woowacourse.movie.domain.model.DateRange
import woowacourse.movie.domain.model.ScreenTimePolicy
import woowacourse.movie.ui.ScreenDetailUI
import java.time.LocalDate
import java.time.LocalTime

interface ScreenDetailContract {
    interface View {
        fun showScreen(screen: ScreenDetailUI)

        fun showTicket(count: Int)

        fun showDateTimePicker(
            dateRange: DateRange,
            screenTimePolicy: ScreenTimePolicy,
        )

        fun navigateToSeatsReservation(timeReservationId: Int)

        fun showToastMessage(e: Throwable)

        fun showSnackBar(e: Throwable)

        fun goToBack(e: Throwable)

        fun unexpectedFinish(e: Throwable)
    }

    interface Presenter {
        fun loadScreen(screenId: Int)

        fun loadTicket()

        fun saveDatePosition(datePosition: Int)

        fun saveTimePosition(timePosition: Int)

        fun saveTicket(count: Int)

        fun plusTicket()

        fun minusTicket()

        fun reserve(screenId: Int)
    }
}
