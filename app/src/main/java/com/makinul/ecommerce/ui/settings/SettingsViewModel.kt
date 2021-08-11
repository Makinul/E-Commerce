package com.makinul.ecommerce.ui.settings

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.makinul.ecommerce.data.model.EmployeeResponse
import com.makinul.ecommerce.data.repository.EmployeeRepository
import com.makinul.ecommerce.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository
) : ViewModel() {

    private val _res = MutableLiveData<Resource<EmployeeResponse>>()
    val res: LiveData<Resource<EmployeeResponse>>
        get() = _res

    init {
        getEmployees()
    }

    private fun getEmployees() {
        viewModelScope.launch {
            _res.postValue(Resource.loading(null))

            employeeRepository.getEmployees().let {
                if (it.isSuccessful) {
                    _res.postValue(Resource.success(it.body()))
                } else {
                    _res.postValue(Resource.error(it.errorBody().toString(), null))
                }
            }
        }

    }
}