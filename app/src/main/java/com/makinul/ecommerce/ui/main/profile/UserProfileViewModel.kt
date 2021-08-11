package com.makinul.ecommerce.ui.main.profile

import androidx.lifecycle.*
import com.makinul.ecommerce.data.model.Random
import com.makinul.ecommerce.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserProfileViewModel @Inject constructor(
    private val userRepository: UserRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
//    val userId: String =
//        savedStateHandle["uid"] ?: throw IllegalArgumentException("missing user id")

    // asLiveData() is part of lifecycle-livedata-ktx
    // https://developer.android.com/kotlin/ktx#livedata
//    val user = userRepository.getUser(userId).asLiveData()

//    val random = userRepository.getRandom()
//    private val random: LiveData<Random> = MutableLiveData()
//
//    val user = random.switchMap {
//        liveData(context = viewModelScope.coroutineContext + Dispatchers.IO) {
//            emit(userRepository.getRandom())
//        }
//    }
//    init {
//        viewModelScope.launch {
//            userRepository.getRandom()
//        }
//    }

    suspend fun getRandom(): LiveData<Random> {
        return userRepository.getRandom()
    }

    fun fetchData() = liveData<Random>(Dispatchers.IO) {
//        emit()
    }
}