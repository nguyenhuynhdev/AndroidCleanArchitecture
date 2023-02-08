package io.nguyenhuynhdev.architecture.app.data.repository

import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.nguyenhuynhdev.architecture.app.domain.repository.Repository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

class RepositoryIml @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : Repository {
    override fun getUsers(): Observable<List<User>> {
        //TODO get data from local data source (database) first, if it's empty, then get data from remote data source (network)
        return remoteDataSource.getUsers().toObservable()
    }
}
