package io.nguyenhuynhdev.architecture.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import io.nguyenhuynhdev.architecture.app.data.repository.RepositoryIml
import io.nguyenhuynhdev.architecture.app.domain.executor.JobExecutor
import io.nguyenhuynhdev.architecture.app.domain.executor.ThreadExecutor
import io.nguyenhuynhdev.architecture.app.domain.repository.Repository

@Module
@InstallIn(FragmentComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindRepository(repository: RepositoryIml): Repository

    @Binds
    abstract fun bindThreadExecutor(jobExecutor: JobExecutor): ThreadExecutor
}