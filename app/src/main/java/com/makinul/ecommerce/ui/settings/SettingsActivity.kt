package com.makinul.ecommerce.ui.settings

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.makinul.ecommerce.R
import com.makinul.ecommerce.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    private val viewModel: SettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.res.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    Log.v("TAG", "SUCCESS")
                }
                Status.LOADING -> {
                    Log.v("TAG", "LOADING")
                }
                Status.ERROR -> {
                    Log.v("TAG", "ERROR")
                }
            }
        })
    }
}