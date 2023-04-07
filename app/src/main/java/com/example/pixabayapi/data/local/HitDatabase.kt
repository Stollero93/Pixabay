package com.example.pixabayapi.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pixabayapi.data.local.entity.HitEntity

@Database(entities = [HitEntity::class], version = 1, exportSchema = false)

abstract class HitDatabase : RoomDatabase() {

    abstract fun hitDao() : HitDao

}