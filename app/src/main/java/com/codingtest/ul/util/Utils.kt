package com.codingtest.ul.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    val snackbar = Snackbar.make(this, message, Snackbar.LENGTH_LONG)
    snackbar.setAction("Ok") {
        snackbar.dismiss()
    }.show()
}
