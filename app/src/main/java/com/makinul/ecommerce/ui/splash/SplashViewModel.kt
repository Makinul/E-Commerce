package com.makinul.ecommerce.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import com.makinul.ecommerce.data.repository.AuthRepository
import com.makinul.ecommerce.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _result = MutableLiveData<Resource<AuthResult>>()
    val result: LiveData<Resource<AuthResult>>
        get() = _result

    fun login() {
        viewModelScope.launch {
            _result.postValue(Resource.loading(null))
            authRepository.anonymousLogin().let {
                if (it == null) {
                    _result.postValue(Resource.error("no user", null))
                } else {
                    _result.postValue(Resource.success(it))
                }
            }
        }
    }

    private val _user = MutableLiveData<Resource<FirebaseUser>>()
    val user: LiveData<Resource<FirebaseUser>>
        get() = _user

    fun currentUser() {
        _user.postValue(Resource.loading(null))
        viewModelScope.launch {
            authRepository.getFirebaseUser().let {
                if (it == null) {
                    _user.postValue(Resource.error("no user", null))
                } else {
                    _user.postValue(Resource.success(it))
                }
            }
        }
    }
}