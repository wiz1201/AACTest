package com.sumin.aactest.data

import androidx.annotation.WorkerThread

class LocalReposirtory(private val usersDao : UserDao) {

    fun getAllUsers() = usersDao.getUserAll()

    /**
     * Local DB에서 유져검색
     * @param userName 검색하고자하는 유져 이름
     * */
    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun findUser(userName: String) {
        usersDao.findUser(userName)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(user: User) {
        usersDao.insert(user)
    }

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun delete(user: User) {
        usersDao.delete(user)
    }

    companion object {
        // For Singleton instantiation
        @Volatile private var instance: LocalReposirtory? = null

        fun getInstance(usersDao : UserDao) =
            instance ?: synchronized(this) {
                instance ?: LocalReposirtory(usersDao).also { instance = it }
            }
    }
}