package com.makinul.ecommerce.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.makinul.ecommerce.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

@AndroidEntryPoint
class UserProfileFragment : Fragment() {
    // To use the viewModels() extension function, include
    // "androidx.fragment:fragment-ktx:latest-version" in your app
    // module's build.gradle file.

    private val viewModel: UserProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.button).setOnClickListener {

        }
//        viewLifecycleOwner.lifecycleScope.launch {
////            val value = viewModel.getRandom().value
////            Log.v("TAG", "TEST")
//            viewModel.getRandom()
//        }
////        viewModel.user.observe(viewLifecycleOwner) {
////            // update UI
////        }
////        // Create a new coroutine in the lifecycleScope
////        viewLifecycleOwner.lifecycleScope.launch {
////            // repeatOnLifecycle launches the block in a new coroutine every time the
////            // lifecycle is in the STARTED state (or above) and cancels it when it's STOPPED.
////            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
////                // Trigger the flow and start listening for values.
////                // This happens when lifecycle is STARTED and stops
////                // collecting when the lifecycle is STOPPED
////                viewModel.user.let {
////
////                }
////            }
////        }
    }

}