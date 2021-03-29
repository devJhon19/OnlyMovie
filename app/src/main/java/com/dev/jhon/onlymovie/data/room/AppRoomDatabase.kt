package com.dev.jhon.onlymovie.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dev.jhon.onlymovie.data.room.dao.MovieDao
import com.dev.jhon.onlymovie.data.room.entity.Movie


@Database(entities = [Movie::class],
          version = 1,
          exportSchema = false)
abstract class AppRoomDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDao

    companion object {
        const val DATABASE_NAME = "dbonlymovie.db"
        const val VERSION_APP = "1.0"

        @Volatile private var INSTANCE: AppRoomDatabase? = null

        fun getInstance(context: Context): AppRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                AppRoomDatabase::class.java, DATABASE_NAME)
                // prepopulate the database after onCreate was called
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                    }
                })
                .fallbackToDestructiveMigration()
                .build()

    }

}
