package com.mbiamont.github.design.card

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import coil.load
import coil.transform.CircleCropTransformation
import com.mbiamont.github.design.R
import kotlinx.android.synthetic.main.card_issue.view.*

class IssueCard @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var author: String
        get() = lblIssueAuthor.text.toString()
        set(value) {
            lblIssueAuthor.text = value
        }

    var title: String
        get() = lblIssueTitle.text.toString()
        set(value) {
            lblIssueTitle.text = value
        }

    var dateLabel: String
        get() = lblIssueDate.text.toString()
        set(value) {
            lblIssueDate.text = value
        }

    var commentsCountLabel: String
        get() = lblIssuesCommentsCount.text.toString()
        set(value) {
            lblIssuesCommentsCount.text = value
        }

    init {
        inflate(context, R.layout.card_issue, this)
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