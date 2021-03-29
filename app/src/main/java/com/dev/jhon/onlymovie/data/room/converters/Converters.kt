package com.dev.jhon.onlymovie.data.room.converters

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("SimpleDateFormat")
class Converters {
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

    private fun parseDate(date : String) : Date {
        val simpleDateFormat = SimpleDateFormat(FORMAT_STRING)
        return simpleDateFormat.parse(date)
    }

    @TypeConverter
    fun fromString(value: String?): ArrayList<Int?>? {
        val listType= object : TypeToken<ArrayList<Int?>?>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Int?>?): String? {
        return Gson().toJson(list)
    }
}