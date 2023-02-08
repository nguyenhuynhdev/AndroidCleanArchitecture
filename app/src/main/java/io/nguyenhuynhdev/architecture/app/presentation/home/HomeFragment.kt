package io.nguyenhuynhdev.architecture.app.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import io.nguyenhuynhdev.architecture.BuildConfig
import io.nguyenhuynhdev.architecture.R
import io.nguyenhuynhdev.architecture.app.domain.executor.DefaultObserver
import io.nguyenhuynhdev.architecture.app.domain.models.User
import io.nguyenhuynhdev.architecture.app.presentation.base.BaseFragment
import io.nguyenhuynhdev.architecture.databinding.FragmentHomeBinding

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    //For activity lifecycle
    private val homeViewModel: HomeViewModel by activityViewModels()
    //For this fragment lifecycle
//    private val homeViewModel: HomeViewModel by viewModels()
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
        homeViewModel.getUsers().execute(GetUsersObserver(), null)
        homeViewModel.chatGpt("Xin chào")

    }

    private inner class GetUsersObserver : DefaultObserver<List<User>>() {
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