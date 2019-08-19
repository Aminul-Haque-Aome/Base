package com.fake_coder.caretutor.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.fake_coder.caretutor.R
import com.fake_coder.caretutor.databinding.ActivitySplashBinding
import com.fake_coder.caretutor.delegates.layoutResource

class SplashActivity : AppCompatActivity() {

    private val binding: ActivitySplashBinding by layoutResource(R.layout.activity_splash)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.lifecycleOwner = this
    }
}
