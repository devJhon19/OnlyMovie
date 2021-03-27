//package com.dev.jhon.onlymovie.data.room.entity
//
//import androidx.room.*
//import com.innova.libaccessibility.data.room.converters.TimeConverter
//import java.util.*
//
//@Entity(tableName = Movie.TABLE_NAME,
//    indices = [Index(value = ["deviceModel", "androidVersion"], unique = true),
//            Index(value = ["serverCode"], unique = true)
//            ]
//)
//@TypeConverters(TimeConverter::class)
//class Movie(@ColumnInfo(name = "deviceModel") val deviceModel: String,
//            @ColumnInfo(name = "androidVersion") val androidVersion: Int,
//            @ColumnInfo(name = "serverCode") val serverCode: Int,    // id
//            @ColumnInfo(name = "creationDate") val creationDate: Date? = Date()
//                    ){
//
//    companion object {
//        const val TABLE_NAME = "TouchPointDevice"
//    }
//
//    @PrimaryKey(autoGenerate = true)
//    @ColumnInfo(name = "id")
//    var id: Int = 0
//}