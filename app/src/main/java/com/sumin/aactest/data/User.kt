package com.sumin.aactest.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(@PrimaryKey @ColumnInfo(name = "user_id") val userID: String, // id
                @ColumnInfo(name = "user_name") val userName: String?,  // login
                @ColumnInfo(name = "user_img") val userImg: String?,    // avartar_url
                @ColumnInfo(name = "like") val like: Boolean = false    // local DB Like
)