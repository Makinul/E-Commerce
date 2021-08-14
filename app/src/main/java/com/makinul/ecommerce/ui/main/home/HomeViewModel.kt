package com.makinul.ecommerce.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makinul.ecommerce.data.model.Product
import com.makinul.ecommerce.data.repository.ProductRepository
import com.makinul.ecommerce.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository
) : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _res = MutableLiveData<Resource<Product>>()
    val res: LiveData<Resource<Product>>
        get() = _res

    init {
//        getAllProducts()
    }

    private fun getAllProducts() {
        viewModelScope.launch {
            _res.postValue(Resource.loading(null))

            productRepository.product("123").let {
//                if (it.isSuccessful) {
//                    _res.postValue(Resource.success(it.body()))
//                } else {
//                    _res.postValue(Resource.error(it.errorBody().toString(), null))
//                }

                Log.v("TAG", "Testing")
            }
        }
    }
}