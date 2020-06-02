package com.mbiamont.github.core.android

import android.graphics.Color

class ColorProvider : IColorProvider {

    override fun parse(color: String?) = Color.parseColor(color)
}