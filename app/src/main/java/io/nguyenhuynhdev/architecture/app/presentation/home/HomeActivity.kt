package io.nguyenhuynhdev.architecture.app.presentation.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.nguyenhuynhdev.architecture.R
import io.nguyenhuynhdev.architecture.app.presentation.base.BaseActivity

class HomeActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}