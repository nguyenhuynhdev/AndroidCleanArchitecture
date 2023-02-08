package io.nguyenhuynhdev.architecture.app.data.repository

import io.nguyenhuynhdev.architecture.app.data.network.ApiService
import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {

    fun getUsers(): Observable<List<User>> {
        return apiService.getUsers()
    }
}