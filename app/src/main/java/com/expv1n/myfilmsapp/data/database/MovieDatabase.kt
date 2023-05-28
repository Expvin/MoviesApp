package com.expv1n.myfilmsapp.data.database

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.expv1n.myfilmsapp.domain.models.MovieEntity


@Database(entities = [MovieEntity::class], version = 1, exportSchema = false)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun getDao(): MovieDao

    companion object {
        private var INSTANCE: MovieDatabase? = null
        private val LOCK = Any()
        private val DB_NAME = "movies.db"

        fun getInstance(application: Application): MovieDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val database = Room.databaseBuilder(
                    application,
                    MovieDatabase::class.java,
                    DB_NAME
                ).build()
                INSTANCE = database
                return database
            }
        }
    }

}