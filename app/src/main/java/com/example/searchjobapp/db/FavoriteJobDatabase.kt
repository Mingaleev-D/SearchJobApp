package com.example.searchjobapp.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.searchjobapp.models.JobToSave

/**
 * @author : Mingaleev D
 * @data : 20/08/2022
 */
@Database(entities = [JobToSave::class], version = 1)
abstract class FavoriteJobDatabase : RoomDatabase() {

    abstract fun getFavJobDao(): FavoriteJobDao

    companion object {
        @Volatile
        private var instance: FavoriteJobDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { instance = it }
        }
        private fun createDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                FavoriteJobDatabase::class.java,
                "fav_job.db"
            ).build()
    }
}