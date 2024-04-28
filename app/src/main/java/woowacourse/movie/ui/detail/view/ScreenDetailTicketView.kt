package woowacourse.movie.ui.detail.view

import android.content.Context
import android.util.AttributeSet
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import woowacourse.movie.R
import woowacourse.movie.ui.detail.ScreenDetailContract

class ScreenDetailTicketView(
    context: Context,
    attrs: AttributeSet? = null,
) : TicketView, ConstraintLayout(context, attrs) {
    private val ticketCount: TextView by lazy { findViewById(R.id.tv_count) }
    private val plusBtn: Button by lazy { findViewById(R.id.btn_plus) }
    private val minusBtn: Button by lazy { findViewById(R.id.btn_minus) }
    private val reserveBtn: Button by lazy { findViewById(R.id.btn_reserve_done) }

    init {
        inflate(context, R.layout.holder_screen_detail_ticket, this)
    }

    override fun initClickListener(
        screenId: Int,
        presenter: ScreenDetailContract.Presenter,
    ) {
        plusBtn.setOnClickListener {
            presenter.plusTicket()
        }
        minusBtn.setOnClickListener {
            presenter.minusTicket()
        }
        reserveBtn.setOnClickListener {
            presenter.reserve(screenId)
        }
    }

    override fun updateTicketCount(count: Int) {
        ticketCount.text = count.toString()
    }

    override fun ticketCount(): Int = ticketCount.text.toString().toInt()

    override fun restoreTicketCount(count: Int) {
        ticketCount.text = count.toString()
    }
}
