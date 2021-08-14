package com.makinul.ecommerce.ui.main.slideshow

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makinul.ecommerce.data.repository.AuthRepository
import com.makinul.ecommerce.data.repository.UserRepository
import com.makinul.ecommerce.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SlideshowViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is slideshow Fragment"
    }

    val text: LiveData<String> = _text

    init {
        login()
    }

    private fun login() {
        viewModelScope.launch {
            authRepository.loginWithEmail("mcnasim@gmail.com", "qweasd")
        }
    }
}