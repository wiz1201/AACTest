package com.sumin.aactest.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getUserAll(): List<User>

    @Query("SELECT * FROM user WHERE user_name LIKE '%'||:userName||'%'")
    fun findUser(userName: String): List<User>

    @Insert
    fun insert(vararg users: User)

    @Delete
    fun delete(user: User)

    @Query("DELETE FROM user")
    fun deleteAll()
}