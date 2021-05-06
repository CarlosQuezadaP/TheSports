package com.condor.thesports.utils

import android.content.Context
import android.widget.Toast

class ToastUtils(private val context: Context) {

    fun showLong(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

    fun showShort(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

}