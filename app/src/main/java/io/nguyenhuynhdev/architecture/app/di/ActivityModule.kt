package io.nguyenhuynhdev.architecture.app.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class ActivityModule {

//    @Binds
//    abstract fun bindNavigator(impl: AppNavigatorImpl): AppNavigator
}