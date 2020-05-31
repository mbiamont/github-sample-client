package com.mbiamont.github.design.card

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import coil.api.load
import coil.transform.CircleCropTransformation
import com.mbiamont.github.design.R
import kotlinx.android.synthetic.main.card_pull_request.view.*

class PullRequestCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var author: String
        get() = lblPullRequestAuthor.text.toString()
        set(value) {
            lblPullRequestAuthor.text = value
        }

    var title: String
        get() = lblPullRequestTitle.text.toString()
        set(value) {
            lblPullRequestTitle.text = value
        }

    var dateLabel: String
        get() = lblPullRequestDate.text.toString()
        set(value) {
            lblPullRequestDate.text = value
        }

    var commentsCountLabel: String
        get() = lblCommentsCount.text.toString()
        set(value) {
            lblCommentsCount.text = value
        }

    init {
        inflate(context, R.layout.card_pull_request, this)
    }

    fun setIndexColor(color: Int) = indexStatus.setBackgroundColor(color)

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