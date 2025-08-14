package com.example.catapp.ui.utils

fun lifeSpanFormatter(lifeSpan: String?): String {
    if (lifeSpan.isNullOrBlank()) return "Unknown"

    val parts = lifeSpan.split("-").map { it.trim() }
    val min = parts.firstOrNull()?.takeIf { it.isNotEmpty() } ?: return "Unknown"

    return min
}


