package io.nguyenhuynhdev.architecture.app.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import io.nguyenhuynhdev.architecture.R
import io.nguyenhuynhdev.architecture.app.domain.executor.DefaultObserver
import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.nguyenhuynhdev.architecture.app.domain.usecases.GetUsersUseCase
import io.nguyenhuynhdev.architecture.app.presentation.base.BaseFragment
import io.nguyenhuynhdev.architecture.databinding.FragmentHomeBinding
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : BaseFragment() {
    @Inject
    lateinit var getUsers: GetUsersUseCase


    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getUsers.execute(getUserObserver(), null)

    }

    private inner class getUserObserver: DefaultObserver<List<User>>() {
        override fun onNext(responseData: List<User>) {
            binding.texf.text = responseData.toString()
        }

        override fun onComplete() {
            super.onComplete()
        }

        override fun onError(exception: Throwable) {
            super.onError(exception)
        }
    }
}