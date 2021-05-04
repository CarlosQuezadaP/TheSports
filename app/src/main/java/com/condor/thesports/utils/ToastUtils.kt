package com.condor.thesports.utils

import android.content.Context
import android.widget.Toast

class ToastUtils(private val context: Context) {


    fun show( message: String) {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show()
    }

}