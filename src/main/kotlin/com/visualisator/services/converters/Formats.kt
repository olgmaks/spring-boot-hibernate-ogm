package com.visualisator.services.converters

import java.text.SimpleDateFormat
import java.util.*


const val ISO_DATE_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ"

val ISO_DATE_FORMAT: SimpleDateFormat = SimpleDateFormat(ISO_DATE_PATTERN)

fun Date.toISOString(): String = ISO_DATE_FORMAT.format(this)

fun String.fromISOString(): Date = if (this.isBlank()) Date() else ISO_DATE_FORMAT.parse(this)