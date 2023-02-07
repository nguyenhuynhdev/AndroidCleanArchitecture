package io.nguyenhuynhdev.architecture.app.data.network

import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ApiService @Inject constructor() {
    fun getUsers(): Single<List<User>> {
        return Single.create { t -> t.onSuccess(arrayListOf(User(803960433, "Nguyen", "Huynh"))) }
    }
}