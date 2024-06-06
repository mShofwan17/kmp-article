package me.project.kmparticle

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.daysUntil
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import kotlin.math.abs

fun String.getDaysAgo(): String {
    val today = Clock.System.todayIn(kotlinx.datetime.TimeZone.currentSystemDefault())
    val days = today.daysUntil(
        Instant.parse(this).toLocalDateTime(kotlinx.datetime.TimeZone.currentSystemDefault()).date
    )

    val result = when {
        abs(days) > 1 -> "${abs(days)} days ago"
        abs(days) == 1 -> "Yesterday"
        else -> "Today"
    }

    return result
}
