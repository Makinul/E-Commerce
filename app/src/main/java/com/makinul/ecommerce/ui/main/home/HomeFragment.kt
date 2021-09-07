package com.makinul.ecommerce.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.makinul.ecommerce.BaseFragment
import com.makinul.ecommerce.databinding.FragmentHomeBinding
import com.makinul.ecommerce.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private lateinit var binding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCategory()
        viewModel.categories.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    showLog()
                }
                Status.LOADING -> {
                    showLog()
                }
                Status.ERROR -> {
                    showLog()
                }
            }
        })
    }

//    private fun asd() {
//        val job = SupervisorJob() // Create a scope which will keep reference to its child jobs
//
//        CoroutineScope(Dispatchers.Main + job).launch {
//            val response = firebaseDatabase.getReference("products/items").singleValueEvent()
//
//            try {
//                response.wait()
//            } catch (e: Exception){
//                e.printStackTrace()
//            }
//            Log.v("TAG", "testing")
//        }
//    }
}