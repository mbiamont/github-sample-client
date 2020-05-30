package com.mbiamont.github.design.card

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import com.mbiamont.github.design.R
import kotlinx.android.synthetic.main.card_repository_extract.view.*

class RepositoryExtractCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var title: String
        get() = lblRepositoryName.text.toString()
        set(value) {
            lblRepositoryName.text = value
        }

    var starLabel: String
        get() = lblStarCount.text.toString()
        set(value) {
            lblStarCount.text = value
        }

    init {
        inflate(context, R.layout.card_repository_extract, this)

        setBackgroundResource(R.drawable.ripple_card)

        val padding = resources.getDimensionPixelSize(R.dimen.medium)
        setPadding(padding, padding, padding, padding)
        elevation = resources.getDimensionPixelSize(R.dimen.small).toFloat()
    }

    fun showLanguage(name: String, color: Int) {
        with(lblLanguage) {
            text = name
            setTextColor(color)
            visibility = View.VISIBLE
        }
    }

    fun hideLanguage() {
        lblLanguage.visibility = View.GONE
    }
}