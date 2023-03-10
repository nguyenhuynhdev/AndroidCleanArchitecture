package io.nguyenhuynhdev.architecture.app.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import io.nguyenhuynhdev.architecture.app.domain.executor.ThreadExecutor
import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.nguyenhuynhdev.architecture.app.domain.repository.Repository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@ViewModelScoped
class GetUsersUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    private val repository: Repository
) :
    UseCase<List<User>, Void?>(threadExecutor) {
    override fun buildUseCaseObservable(params: Void?): Observable<List<User>> {
        return repository.getUsers()
    }
}