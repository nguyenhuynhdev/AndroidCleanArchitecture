package io.nguyenhuynhdev.architecture.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ViewModelScoped
import io.nguyenhuynhdev.architecture.app.data.repository.RepositoryIml
import io.nguyenhuynhdev.architecture.app.domain.executor.ThreadExecutor
import io.nguyenhuynhdev.architecture.app.domain.executor.ThreadExecutorIml
import io.nguyenhuynhdev.architecture.app.domain.repository.Repository
import javax.inject.Singleton

@InstallIn(ActivityComponent::class)
@Module
abstract class ActivityModule {

}