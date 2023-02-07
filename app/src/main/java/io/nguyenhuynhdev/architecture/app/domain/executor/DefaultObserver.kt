package io.nguyenhuynhdev.architecture.app.domain.executor

import io.reactivex.rxjava3.observers.DisposableObserver

open class DefaultObserver<T : Any> : DisposableObserver<T>() {

    override fun onNext(responseData: T) {}

    override fun onComplete() {}

    override fun onError(exception: Throwable) {}
}