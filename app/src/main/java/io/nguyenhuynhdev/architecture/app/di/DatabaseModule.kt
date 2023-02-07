package io.nguyenhuynhdev.architecture.app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.nguyenhuynhdev.architecture.app.data.database.AppDatabase
import io.nguyenhuynhdev.architecture.app.data.database.dao.UserDao
import io.nguyenhuynhdev.architecture.app.data.repository.RepositoryIml
import io.nguyenhuynhdev.architecture.app.domain.repository.Repository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "Architecture.db"
        ).build()
    }

//    @Provides
//    fun provideRepository(): Repository{
//        return RepositoryIml()
//    }

}