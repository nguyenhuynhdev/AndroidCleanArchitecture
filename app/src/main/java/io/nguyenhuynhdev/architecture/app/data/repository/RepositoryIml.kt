package io.nguyenhuynhdev.architecture.app.data.repository

import android.util.Log
import io.nguyenhuynhdev.architecture.BuildConfig
import io.nguyenhuynhdev.architecture.app.data.network.OpenAIService
import io.nguyenhuynhdev.architecture.app.domain.executor.DefaultObserver
import io.nguyenhuynhdev.architecture.app.domain.executor.ThreadExecutor
import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.nguyenhuynhdev.architecture.app.domain.models.openai.Request
import io.nguyenhuynhdev.architecture.app.domain.repository.Repository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.*
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.functions.Action
import io.reactivex.rxjava3.schedulers.Schedulers
import org.reactivestreams.Subscription
import java.util.function.Consumer
import javax.inject.Inject

class RepositoryIml @Inject constructor(
    private val openAIService: OpenAIService,
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val threadExecutor: ThreadExecutor
) : Repository {

    private val disposable = CompositeDisposable()
    override fun getUsers(): Observable<List<User>> {

        return Observable.create { e ->
            disposable.add(localDataSource.getUsers().compose(applySchedulersFlow())
                .doOnNext { users ->
                    if (users.isEmpty()) {
                        remoteDataSource.getUsers().compose(applySchedulers())
                            .doOnNext { response ->
                                Log.i("Repository", "Loading api complete")
                                localDataSource.saveUsers(response)
                                    .subscribeOn(Schedulers.from(threadExecutor))
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .unsubscribeOn(Schedulers.io())
                                    .doOnSubscribe { e.onNext(response) }
                                    .doOnComplete { e.onComplete() }
                                    .doOnError { e.onError(it) }
                                    .doFinally { disposable.clear() }
                                    .subscribe()
                            }
                            .doOnError { e.onError(it) }
                            .doOnComplete { e.onComplete() }
                            .subscribe()
                    } else {
                        Log.i("Repository", "Load from local")
                        e.onNext(users)
                    }
                }
                .doOnError { e.onError(it) }
                .doOnComplete { e.onComplete() }
                .doFinally { disposable.clear() }
                .subscribe()
            )
        }
    }

    override fun chatGpt(prompt: String): Observable<String> {
        return openAIService.generateResponse(
            "Bearer ${BuildConfig.GPT_KEY}",
            Request(model = "text-davinci-003", prompt = prompt, max_tokens = 7, temperature = 0f),
        )
            .map { it.choices[0].text }
    }

    private fun <T : Any> applySchedulers(): ObservableTransformer<T, T> {
        return ObservableTransformer<T, T> { observable ->
            observable.subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
        }
    }

    private fun <T : Any> applySchedulersFlow(): FlowableTransformer<T, T> {
        return FlowableTransformer<T, T> { observable ->
            observable.subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
        }
    }
}

