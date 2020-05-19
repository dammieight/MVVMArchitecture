package com.example.mvvmarchitecture.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.mvvmarchitecture.data.database.dao.QuoteDao
import com.example.mvvmarchitecture.data.database.dao.UserDao
import com.example.mvvmarchitecture.data.database.entities.Quote
import com.example.mvvmarchitecture.data.database.entities.User


@Database(
    entities = [User::class, Quote::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getUserDao(): UserDao
    abstract fun getQuoteDao(): QuoteDao

    companion object {  /*same as static obj in java*/

        @Volatile //make instance variable visible to other threads
        private var instance: AppDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "AppDatabase.db"
            ).build()
    }
}