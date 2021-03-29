package com.dev.jhon.onlymovie.domain.utils

import android.annotation.SuppressLint
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

class Util {
    companion object{
        @SuppressLint("SimpleDateFormat")
        fun parseDateString(date: Date?) : String{
            return try {
                val pattern = "dd-MM-yyyy"
                val simpleDateFormat = SimpleDateFormat(pattern)
                simpleDateFormat.format(Date())
            }catch (e : Exception){
                ""
            }
        }
    }
}