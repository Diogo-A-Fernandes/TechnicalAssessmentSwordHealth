package com.example.catapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.catapp.data.entity.CatBreedEntity

@Dao
interface CatBreedDao {

    @Query("SELECT * FROM cat_breeds")
    suspend fun getAllCatsList(): List<CatBreedEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cats: List<CatBreedEntity>)

}