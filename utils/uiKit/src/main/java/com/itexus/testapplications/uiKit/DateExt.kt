package com.itexus.testapplications.uiKit

import io.github.aakira.napier.Napier
import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDate(pattern: String): String {
    val dateFormat = SimpleDateFormat(pattern, Locale.US)
    return try {
        dateFormat.format(this)
    } catch (e: IllegalArgumentException) {
        Napier.e(e.toString())
        toString()
    }
}

const val DATE_FORMAT = "MMM dd, yyyy"