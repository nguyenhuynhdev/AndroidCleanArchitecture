package io.nguyenhuynhdev.architecture.app.data.repository

import io.nguyenhuynhdev.architecture.app.data.database.dao.UserDao
import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

// LocalDataSource.kt
class LocalDataSource @Inject constructor(private val userDao: UserDao) {

    fun getUsers(): Flowable<List<User>> {
        return userDao.getUsers()
    }

    fun saveUsers(it: List<User>) {
        userDao.insertAll(*it.toTypedArray())
    }
}