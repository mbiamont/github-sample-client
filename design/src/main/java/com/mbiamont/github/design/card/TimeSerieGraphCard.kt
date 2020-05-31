package com.mbiamont.github.design.card

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.jonas.jgraph.models.Jchart
import com.mbiamont.github.design.R
import kotlinx.android.synthetic.main.card_time_serie_graph.view.*

class TimeSerieGraphCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var title: String
        get() = titleGraph.text.toString()
        set(value) {
            titleGraph.text = value
        }

    init {
        inflate(context, R.layout.card_time_serie_graph, this)

        val padding = resources.getDimensionPixelSize(R.dimen.small)
        setPadding(padding, padding, padding, padding)
        graphLayout.setPaintShaderColors(
            resources.getColor(R.color.lightViolet),
            resources.getColor(R.color.violet),
            resources.getColor(R.color.darkViolet)
        )
    }

    fun showLoader() {
        loader.visibility = View.VISIBLE
    }

    fun hideLoader(){
        loader.visibility = View.GONE
    }

    fun showDatas(datas: Array<Int>) {
        loader.visibility = View.GONE
        graphLayout.visibility = View.VISIBLE

        val jDatas = datas.mapIndexed { index, i -> Jchart(0F, i.toFloat(), index.toString()) }

        graphLayout.feedData(jDatas)
    }
}