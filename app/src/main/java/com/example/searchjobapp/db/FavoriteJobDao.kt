package com.example.searchjobapp.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.searchjobapp.models.JobToSave

/**
 * @author : Mingaleev D
 * @data : 20/08/2022
 */
@Dao
interface FavoriteJobDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addFavoriteJob(job:JobToSave)

    @Query("SELECT * FROM job ORDER BY id DESC ")
    fun getAllFavJob():LiveData<List<JobToSave>>

    @Delete
    suspend fun deleteFavJob(job:JobToSave)
}