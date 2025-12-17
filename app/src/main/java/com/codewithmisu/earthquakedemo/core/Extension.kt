package com.codewithmisu.earthquakedemo.core

import java.math.BigDecimal
import java.math.RoundingMode

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Double.roundUpTo2Decimals(): Double {
    return BigDecimal(this)
        .setScale(2, RoundingMode.UP)
        .toDouble()
}


fun Long.toFormattedDateTime(): String {
    val instant = Instant.ofEpochMilli(this)
    val zone = ZoneId.systemDefault()
    val formatter = DateTimeFormatter.ofPattern("MMM-dd, hh:mm a")
    return instant.atZone(zone).format(formatter)
}