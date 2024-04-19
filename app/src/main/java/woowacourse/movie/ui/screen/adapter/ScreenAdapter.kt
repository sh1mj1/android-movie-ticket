package woowacourse.movie.ui.screen.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import woowacourse.movie.R
import woowacourse.movie.domain.model.Screen
import woowacourse.movie.ui.detail.ScreenDetailActivity

class ScreenAdapter(private var item: List<Screen>) : BaseAdapter() {
    override fun getCount(): Int = item.size

    override fun getItem(position: Int): Screen = item[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(
        position: Int,
        convertView: View?,
        parent: ViewGroup,
    ): View {
        val view = convertView ?: LayoutInflater.from(parent.context).inflate(R.layout.holder_screen, parent, false)
        initBinding(view, position)
        initClickListener(view, parent.context, position)

        return view
    }

    private fun initBinding(
        view: View,
        position: Int,
    ) {
        val poster = view.findViewById<ImageView>(R.id.iv_poster)
        val title = view.findViewById<TextView>(R.id.tv_title)
        val date = view.findViewById<TextView>(R.id.tv_screen_date)
        val runningTime = view.findViewById<TextView>(R.id.tv_screen_running_time)

        with(item[position]) {
            poster.setImageResource(movie.imageSrc)
            title.text = movie.title
            date.text = this.date
            runningTime.text = movie.runningTime.toString()
        }
    }

    private fun initClickListener(
        view: View,
        context: Context,
        position: Int,
    ) {
        val reserveButton = view.findViewById<Button>(R.id.btn_reserve_now)

        reserveButton.setOnClickListener {
            ScreenDetailActivity.startActivity(context, item[position].id)
        }
    }

    fun updateScreens(screens: List<Screen>) {
        item = screens
        notifyDataSetChanged()
    }
}
