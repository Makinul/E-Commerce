package com.makinul.ecommerce.ui.splash

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.android.hilt.navigator.AppNavigator
import com.example.android.hilt.navigator.Screens
import com.makinul.ecommerce.R
import com.makinul.ecommerce.navigator.AppNavigatorImpl
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var navigator: AppNavigator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (savedInstanceState == null) {
            navigator.navigateTo(Screens.SPLASH)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}