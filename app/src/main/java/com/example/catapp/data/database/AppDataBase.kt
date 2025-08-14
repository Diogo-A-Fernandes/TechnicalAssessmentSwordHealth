package com.example.catapp.data.database


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.catapp.data.dao.CatBreedDao
import com.example.catapp.data.entity.CatBreedEntity

@Database(entities = [CatBreedEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun catBreedDao(): CatBreedDao

}