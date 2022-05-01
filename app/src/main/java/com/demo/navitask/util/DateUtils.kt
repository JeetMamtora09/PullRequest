package com.demo.navitask.util

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

fun getAbbreviatedFromDateTime(dateTime: String, dateFormat: String = "yyyy-mm-dd", field: String="MMM dd yyyy"): String? {
    val input = SimpleDateFormat(dateFormat, Locale.ENGLISH)
    val output = SimpleDateFormat(field,Locale.ENGLISH)
    try {
        val getAbbreviate = input.parse(dateTime)    // parse input
        return output.format(getAbbreviate)    // format output
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return null
}