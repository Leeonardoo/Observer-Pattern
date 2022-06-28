package com.github.leeonardoo.observer.util

import android.view.View
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding

fun View.applyNavbarInsets() {
    ViewCompat.setOnApplyWindowInsetsListener(this) { _, insets ->
        val systemWindowInsets = insets.getInsets(WindowInsetsCompat.Type.systemBars())
        updatePadding(bottom = systemWindowInsets.bottom)
        insets
    }
}