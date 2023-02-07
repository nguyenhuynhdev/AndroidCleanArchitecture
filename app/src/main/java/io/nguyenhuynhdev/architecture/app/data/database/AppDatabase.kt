package io.nguyenhuynhdev.architecture.app.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.nguyenhuynhdev.architecture.app.data.database.dao.UserDao
import io.nguyenhuynhdev.architecture.app.domain.models.User

@Database(entities = [User::class], version = 2, exportSchema = false, autoMigrations = [])
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}