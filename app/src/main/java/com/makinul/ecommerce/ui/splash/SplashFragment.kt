package com.makinul.ecommerce.ui.splash

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.viewModels
import com.makinul.ecommerce.BaseFragment
import com.makinul.ecommerce.R
import com.makinul.ecommerce.databinding.FragmentSplashBinding
import com.makinul.ecommerce.ui.main.MainActivity
import com.makinul.ecommerce.ui.main.home.HomeViewModel
import com.makinul.ecommerce.util.DateFormatter
import com.makinul.ecommerce.util.Resource
import com.makinul.ecommerce.util.Status
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

    @Inject
    lateinit var dateFormatter: DateFormatter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    private val viewModel: SplashViewModel by viewModels()
    private lateinit var binding: FragmentSplashBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSplashBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        holder.textView.text = "${log.msg}\n\t${daterFormatter.formatDate(log.timestamp)}"
//        val currentDate = dateFormatter.formatDate(System.currentTimeMillis())
//        Log.v(TAG, "currentDate: $currentDate")
//        binding.allLogs.setOnClickListener {
//            val mainIntent = Intent(activity, MainActivity::class.java)
//            requireActivity().startActivity(mainIntent)
//            requireActivity().finish()
//        }
        viewModel.currentUser()
        viewModel.user.observe(viewLifecycleOwner, {
            showLog(it.status.toString())

            when (it.status) {
                Status.SUCCESS -> {
//                    binding.progress.visibility = View.GONE
//                    binding.recyclerView.visibility = View.VISIBLE
//                    it.data.let { res ->
//                        if (res?.status == "success") {
//                            res.data?.let { it1 -> adapter.submitList(it1) }
//                        } else {
//                            Snackbar.make(binding.root, "Status = false", Snackbar.LENGTH_SHORT).show()
//                        }
//                    }
                }
                Status.LOADING -> {
//                    binding.progress.visibility = View.VISIBLE
//                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
//                    binding.progress.visibility = View.GONE
//                    binding.recyclerView.visibility = View.VISIBLE
//                    Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }

    companion object {
        const val TAG = "SplashFragment"
    }
}