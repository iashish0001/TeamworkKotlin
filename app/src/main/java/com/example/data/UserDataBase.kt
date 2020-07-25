package com.example.data


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.modules.User


@Database(entities = [User::class], version = 1, exportSchema = false)
abstract class UserDataBase : RoomDatabase() {
    abstract val userDao: UserDao?
}
