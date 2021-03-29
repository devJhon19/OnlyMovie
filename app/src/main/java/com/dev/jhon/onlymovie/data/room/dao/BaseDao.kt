package com.dev.jhon.onlymovie.data.room.dao

import androidx.room.*

@Dao
interface BaseDao <T>{

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(entity: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAll(entities: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertAll(entities: List<T>)

    @Update
    fun update(vararg entity: T)
    @Delete
    fun delete(vararg entity: T)

}