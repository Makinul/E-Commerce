package com.makinul.ecommerce.ui.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makinul.ecommerce.data.local.CategoryDao
import com.makinul.ecommerce.data.model.Category
import com.makinul.ecommerce.data.model.Product
import com.makinul.ecommerce.data.model.SubCategory
import com.makinul.ecommerce.data.repository.ProductRepository
import com.makinul.ecommerce.util.Resource
import com.makinul.ecommerce.util.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val productRepository: ProductRepository,
    private val categoryDao: CategoryDao
) : ViewModel() {

    private val _res = MutableLiveData<Resource<List<Product>>>()
    val res: LiveData<Resource<List<Product>>>
        get() = _res

    private fun getAllProducts() {
        viewModelScope.launch {
            _res.postValue(Resource.loading(null))
            productRepository.allProducts().let {
                Log.v("TAG", "Testing")
            }
        }
    }

    private val _categories = MutableLiveData<Resource<List<Category>>>()
    val categories: LiveData<Resource<List<Category>>>
        get() = _categories

    fun getCategories() {
        viewModelScope.launch {
            _categories.postValue(Resource.loading(null))
            productRepository.localCategories().let {
                _categories.postValue(it)
            }
            productRepository.allCategories().let {
                _categories.postValue(it)
            }
        }
    }

    private val _subCategories = MutableLiveData<Resource<List<SubCategory>>>()
    val subCategories: LiveData<Resource<List<SubCategory>>>
        get() = _subCategories

    fun getSubCategories(categoryId: String) {
        viewModelScope.launch {
            _subCategories.postValue(Resource.loading(null))
            productRepository.getSubCategories(categoryId).let {
                _subCategories.postValue(it)
            }
        }
    }
}