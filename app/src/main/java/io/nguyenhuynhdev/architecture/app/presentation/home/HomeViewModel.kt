package io.nguyenhuynhdev.architecture.app.presentation.home

import android.util.Log
import androidx.lifecycle.LifecycleObserver
import dagger.hilt.android.lifecycle.HiltViewModel
import io.nguyenhuynhdev.architecture.app.domain.executor.DefaultObserver
import io.nguyenhuynhdev.architecture.app.domain.usecases.ChatGptUseCase
import io.nguyenhuynhdev.architecture.app.domain.usecases.GetUsersUseCase
import io.nguyenhuynhdev.architecture.app.presentation.base.BaseViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val usersUseCase: GetUsersUseCase,
    private val chatGptUseCase: ChatGptUseCase
) :
    BaseViewModel(), LifecycleObserver {

    fun getUsers() = usersUseCase

    fun chatGpt(question: String) {
        chatGptUseCase.execute(object : DefaultObserver<String>() {
            override fun onNext(responseData: String) {
                Log.i("ChatGpt:", responseData)
            }
        }, question)
    }


}