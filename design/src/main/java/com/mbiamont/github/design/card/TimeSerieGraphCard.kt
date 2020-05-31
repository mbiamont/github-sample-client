package com.mbiamont.github.design.card

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jonas.jgraph.graph.JcoolGraph
import com.jonas.jgraph.models.Jchart
import com.mbiamont.github.design.R
import kotlinx.android.synthetic.main.card_time_serie_graph.view.*

class TimeSerieGraphCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.card_time_serie_graph, this)

        val padding = resources.getDimensionPixelSize(R.dimen.small)
        setPadding(padding, padding, padding, padding)
    }

    fun hideAll(){
        loader.visibility = View.GONE
        graphLayout.visibility = View.GONE
    }

    fun showLoader(count: Int, totalCount: Int, loaderLabel: String) {
        loader.visibility = View.VISIBLE
        graphLayout.visibility = View.GONE

        loader.maxProgress = totalCount.toDouble()
        loader.setCurrentProgress(count.toDouble())
        loader.setProgressTextAdapter {
            loaderLabel
        }
    }

    fun showDatas(datas: Array<Int>) {
        loader.visibility = View.GONE
        graphLayout.visibility = View.VISIBLE

        val jDatas = mutableListOf<Jchart>()

        datas.forEachIndexed { index, i ->
            jDatas.add(Jchart(0F, i.toFloat(), index.toString()))
        }

        graphLayout.feedData(jDatas)
    }
}