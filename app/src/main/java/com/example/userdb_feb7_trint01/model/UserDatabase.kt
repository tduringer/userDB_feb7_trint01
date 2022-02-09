package com.example.userdb_feb7_trint01.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [UserEntity::class], version = 1, exportSchema = true)
abstract class UserDatabase : RoomDatabase() {

    companion object {
        const val DB_NAME = "user_database"
        private lateinit var context : Context

        private val database : UserDatabase by lazy(LazyThreadSafetyMode.SYNCHRONIZED){
            Room.databaseBuilder(context,UserDatabase::class.java, DB_NAME).build()
        }

        fun getDatabase(context: Context) : UserDatabase {
            this.context = context.applicationContext
            return database
        }
    }

    abstract fun userDao() : UserDao
}