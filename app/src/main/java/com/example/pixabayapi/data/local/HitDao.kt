package com.example.pixabayapi.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pixabayapi.data.local.entity.HitEntity

@Dao
interface HitDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: HitEntity)

    @Query("DELETE FROM HitEntity")
    fun deleteAllLocal()

    @Query("SELECT * FROM HitEntity")
    fun getAllLocal() : List<HitEntity>

}