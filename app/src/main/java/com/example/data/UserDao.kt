package com.example.data


import androidx.room.*
import com.example.modules.User


@Dao
interface UserDao {
    @Query("SELECT * FROM User where email= :mail and password= :password")
    fun getUser(mail: String?, password: String?): User?

    @Insert
    fun insert(user: User?)

    @Update
    fun update(user: User?)

    @Delete
    fun delete(user: User?)
}