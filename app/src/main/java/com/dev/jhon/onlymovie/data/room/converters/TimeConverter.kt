package com.innova.libaccessibility.data.room.converters

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.room.TypeConverter
import java.text.SimpleDateFormat
import java.time.Instant
import java.util.*

@SuppressLint("SimpleDateFormat")
class TimeConverter {
//    https://stackoverflow.com/questions/7487460/converting-long-to-date-in-java-returns-1970
//    https://www.w3resource.com/sqlite/sqlite-strftime.php
//    SELECT strftime('%H %M %S %s','now');
    companion object{
        val FORMAT_STRING = "yyyy-MM-dd HH:mm:ss"
    }

    @TypeConverter
    fun strintToDate(value: String): Date {
        return parseDate(value)
    }

    @TypeConverter
    fun dateToString(date: Date): String {
        val simpleDateFormat = SimpleDateFormat(FORMAT_STRING)
        return simpleDateFormat.format(date)
    }

    private fun parseDate(date : String) : Date{
        val simpleDateFormat = SimpleDateFormat(FORMAT_STRING)
        return simpleDateFormat.parse(date)
    }

}