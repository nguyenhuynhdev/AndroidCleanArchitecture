package io.nguyenhuynhdev.architecture.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.nguyenhuynhdev.architecture.app.data.database.dao.UserDao
import io.nguyenhuynhdev.architecture.app.data.database.entity.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}