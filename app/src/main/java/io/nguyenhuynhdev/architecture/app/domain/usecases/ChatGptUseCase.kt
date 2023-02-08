package io.nguyenhuynhdev.architecture.app.domain.usecases

import dagger.hilt.android.scopes.ViewModelScoped
import io.nguyenhuynhdev.architecture.app.domain.executor.ThreadExecutor
import io.nguyenhuynhdev.architecture.app.domain.repository.Repository
import io.reactivex.rxjava3.core.Observable
import javax.inject.Inject

@ViewModelScoped
class ChatGptUseCase @Inject constructor(
    threadExecutor: ThreadExecutor,
    private val repository: Repository
) : UseCase<String, String>(threadExecutor){

    override fun buildUseCaseObservable(params: String): Observable<String> {
        return repository.chatGpt(params)
    }

}