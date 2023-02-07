package io.nguyenhuynhdev.architecture.app.domain.repository

import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.reactivex.rxjava3.core.Observable

interface Repository {

    fun getUsers(): Observable<List<User>>
}