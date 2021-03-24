package com.mbiamont.github.design.card

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import coil.transform.CircleCropTransformation
import com.mbiamont.github.design.R
import kotlinx.android.synthetic.main.card_fork.view.*
import kotlinx.android.synthetic.main.card_pull_request.view.icAuthorAvatar

class ForkCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var author: String
        get() = lblForkAuthor.text.toString()
        set(value) {
            lblForkAuthor.text = value
        }

    var dateLabel: String
        get() = lblForkDate.text.toString()
        set(value) {
            lblForkDate.text = value
        }

    init {
        inflate(context, R.layout.card_fork, this)
    }

    fun removeAvatar() {
        icAuthorAvatar.visibility = View.INVISIBLE
    }

    fun setAvatarUrl(url: String) {
        icAuthorAvatar.load(url) {
            transformations(CircleCropTransformation())
        }

        icAuthorAvatar.visibility = View.VISIBLE
    }
}