package com.mbiamont.github.design.navigation

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import com.mbiamont.github.design.R
import com.mbiamont.github.design.extensions.enableSelectableBackground
import kotlinx.android.synthetic.main.layout_bottom_navigation_bar.view.*

class BottomNavigationBar @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    var onTabItemSelected: (id: Int) -> Any = {}

    var selectedTab: Int
        get() = currentPosition
        set(value) {
            tabs.forEachIndexed { index, imageView ->
                imageView.isSelected = index == value
                currentPosition = value
            }
        }

    private var currentPosition = -1

    private val tabs: Array<ImageView>

    init {
        inflate(context, R.layout.layout_bottom_navigation_bar, this)
        setBackgroundResource(R.color.black)

        tabs = arrayOf(menuIssues, menuPullRequests, menuForks)

        setupClickListeners()

        selectedTab = TAB_ISSUES
    }

    private fun setupClickListeners() {
        tabs.forEachIndexed { index, imageView ->
            imageView.enableSelectableBackground()

            imageView.setOnClickListener {
                navigateToTab(index)
            }
        }
    }

    private fun navigateToTab(position: Int) {
        tabs.forEachIndexed { index, imageView ->
            imageView.isSelected = index == position

            if (index == position && position != currentPosition) {
                onTabItemSelected(position)
                currentPosition = position
            }
        }
    }
}

const val TAB_ISSUES = 0
const val TAB_PULL_REQUESTS = 1
const val TAB_FORKS = 2