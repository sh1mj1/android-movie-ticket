package woowacourse.movie.ui.screen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import woowacourse.movie.R
import woowacourse.movie.domain.repository.DummyMovies
import woowacourse.movie.domain.repository.DummyScreens
import woowacourse.movie.ui.ScreenPreviewUI
import woowacourse.movie.ui.detail.ScreenDetailActivity
import woowacourse.movie.ui.screen.adapter.ScreenAdapter
import woowacourse.movie.ui.screen.adapter.ScreenAd

class ScreenActivity : AppCompatActivity(), ScreenContract.View {
    private lateinit var adapter: ScreenAdapter
    private val screenPresenter: ScreenContract.Presenter by lazy {
        ScreenPresenter(
            this,
            DummyMovies(),
            DummyScreens(),
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initAdapter()
        screenPresenter.loadScreen2()
    }

    private fun initAdapter() {
        val recyclerView = findViewById<RecyclerView>(R.id.lv_screen)
        adapter =
            ScreenAdapter {screenId ->
                ScreenDetailActivity.startActivity(this, screenId)
            }
        recyclerView.adapter = adapter
    }

    override fun showScreens(screens: List<ScreenPreviewUI>) {
//        adapter.submitList(screens)
    }

    override fun showScreens2(screens: List<ScreenAd>) {
        adapter.submitList(screens)
    }
}
