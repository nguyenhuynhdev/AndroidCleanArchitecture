package io.nguyenhuynhdev.architecture.app.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.nguyenhuynhdev.architecture.app.data.repository.RepositoryIml
import io.nguyenhuynhdev.architecture.app.domain.repository.Repository

@Module
@InstallIn(ViewModelComponent::class)
abstract class ViewModelModule {
    @Binds
    abstract fun bindRepository(repository: RepositoryIml): Repository

}