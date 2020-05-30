package com.mbiamont.github.design.extensions

import android.util.TypedValue
import android.view.View
import com.mbiamont.github.design.R

fun View.enableSelectableBackground() = with(TypedValue()) {
    context.theme.resolveAttribute(R.attr.selectableItemBackground, this, true)
    setBackgroundResource(resourceId)
}