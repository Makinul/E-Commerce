package com.makinul.ecommerce.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.makinul.ecommerce.BaseFragment
import com.makinul.ecommerce.databinding.FragmentSplashBinding
import com.makinul.ecommerce.ui.main.MainActivity
import com.makinul.ecommerce.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashFragment : BaseFragment() {

//    @Inject
//    lateinit var dateFormatter: DateFormatter
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }

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

        viewModel.currentUser()
        viewModel.user.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
//                    binding.progressBar.visibility = View.GONE

                    handler.postDelayed(runnable, 2000)
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    doLogin()
                }
            }
        })
    }

    private fun doLogin() {
        viewModel.login()
        viewModel.result.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progressBar.visibility = View.GONE
                    startMainActivity()
                }
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    binding.progressBar.visibility = View.GONE
                    Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_INDEFINITE)
                        .setAction("Retry") {
                            doLogin()
                        }
                        .show()
                }
            }
        })
    }

    private val handler = Handler(Looper.getMainLooper())

    private val runnable = Runnable {
        startMainActivity()
    }

    private fun startMainActivity() {
        val intentMain = Intent(activity, MainActivity::class.java)
        requireActivity().startActivity(intentMain)
        requireActivity().finish()
    }

    companion object {
        const val TAG = "SplashFragment"
    }
}