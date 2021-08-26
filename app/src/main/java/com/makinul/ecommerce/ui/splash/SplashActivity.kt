package com.makinul.ecommerce.ui.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.makinul.ecommerce.databinding.ActivitySplashBinding
import com.makinul.ecommerce.navigator.AppNavigator
import com.makinul.ecommerce.navigator.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            navigator.navigateTo(Screens.SPLASH)
        }
    }

    override fun onBackPressed() {
        finish()
    }
}