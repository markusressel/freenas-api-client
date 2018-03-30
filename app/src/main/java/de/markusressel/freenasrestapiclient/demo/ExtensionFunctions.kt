package de.markusressel.freenasrestapiclient.demo

import android.content.Context
import android.widget.Toast

/**
 * Show a simple toast message
 */
fun Context.showToastLong(message: String) {
    showToast(this, message, Toast.LENGTH_LONG)
}

/**
 * Show a simple toast message
 */
fun Context.showToastShort(message: String) {
    showToast(this, message, Toast.LENGTH_SHORT)
}

private fun showToast(context: Context, message: String, length: Int) {
    Toast
            .makeText(context, message, length)
            .show()
}