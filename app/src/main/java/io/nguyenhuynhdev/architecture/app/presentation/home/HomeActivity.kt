package io.nguyenhuynhdev.architecture.app.presentation.home

import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import dagger.hilt.android.AndroidEntryPoint
import io.nguyenhuynhdev.architecture.R
import io.nguyenhuynhdev.architecture.app.presentation.base.BaseActivity
import io.nguyenhuynhdev.architecture.databinding.ActivityHomeBinding

@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding= DataBindingUtil.setContentView(this, R.layout.activity_home)
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onStop() {
        super.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}