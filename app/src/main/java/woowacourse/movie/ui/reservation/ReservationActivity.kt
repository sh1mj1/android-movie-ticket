package woowacourse.movie.ui.reservation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import woowacourse.movie.R
import woowacourse.movie.domain.model.Reservation
import woowacourse.movie.domain.repository.DummyReservation2
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.Locale

class ReservationActivity : AppCompatActivity(), ReservationContract2.View {
    private val presenter: ReservationContract2.Presenter by lazy { ReservationPresenter2(this, DummyReservation2) }

    private val title: TextView by lazy { findViewById(R.id.tv_reservation_title) }
    private val date: TextView by lazy { findViewById(R.id.tv_reservation_date) }
    private val count: TextView by lazy { findViewById(R.id.tv_reservation_count) }
    private val amount: TextView by lazy { findViewById(R.id.tv_reservation_amount) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservation)

        initView()
    }

    private fun initView() {
        val id = intent.getIntExtra(PUT_EXTRA_KEY_RESERVATION_ID, DEFAULT_RESERVATION_ID)
        presenter.loadReservation(id)
    }

    override fun showReservation(reservation: Reservation) {
        with(reservation) {
            title.text = screen.movie.title
            date.text = screen.date
            count.text = getString(R.string.reserve_count).format(this.ticket.count)
            amount.text = currency()
        }
    }

    private fun Reservation.currency(): String {
        return getString(R.string.reserve_amount).format(
            when (Locale.getDefault().country) {
                Locale.KOREA.country -> DecimalFormat("#,###원").format(totalPrice)
                else -> NumberFormat.getCurrencyInstance(Locale.getDefault()).format(totalPrice)
            },
        )
    }

    override fun showToastMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    override fun showSnackBar(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
    }

    override fun goToBack(message: String) {
        showToastMessage(message)
        finish()
    }

    override fun unexpectedFinish(message: String) {
        showSnackBar(message)
        finish()
    }

    companion object {
        private const val PUT_EXTRA_KEY_RESERVATION_ID = "reservationId"
        private const val DEFAULT_RESERVATION_ID = -1

        fun startActivity(
            context: Context,
            reservationId: Int,
        ) {
            val intent = Intent(context, ReservationActivity::class.java)
            intent.putExtra(PUT_EXTRA_KEY_RESERVATION_ID, reservationId)
            context.startActivity(intent)
        }
    }
}
