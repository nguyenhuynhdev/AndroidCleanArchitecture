package io.nguyenhuynhdev.architecture.app.data.network

import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET

interface ApiService {

    @GET("users.json")
    fun getUsers(): Observable<List<User>>
}