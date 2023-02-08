package io.nguyenhuynhdev.architecture.app.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.nguyenhuynhdev.architecture.BuildConfig
import io.nguyenhuynhdev.architecture.app.data.database.AppDatabase
import io.nguyenhuynhdev.architecture.app.data.database.dao.UserDao
import io.nguyenhuynhdev.architecture.app.data.network.ApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ApplicationModule {

    @Provides
    @Singleton
    fun providerInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    @Provides
    @Singleton
    fun providerClient(interceptor: HttpLoggingInterceptor): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .connectTimeout(20, TimeUnit.SECONDS)
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(interceptor)
        }
        return builder.build()
    }

    @Provides
    @Singleton
    fun providerRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
    }

    @Provides
    @Singleton
    fun providerApiService(retrofit: Retrofit) : ApiService {
        return retrofit.create(ApiService::class.java)
    }

    //Database
    @Provides
    fun provideUserDao(database: AppDatabase): UserDao {
        return database.userDao()
    }

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext, AppDatabase::class.java, "Architecture.db"
        ).build()
    }
}