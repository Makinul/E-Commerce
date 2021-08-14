package com.makinul.ecommerce.ui.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.makinul.ecommerce.R
import com.makinul.ecommerce.data.repository.ProductRepositoryImpl
import com.makinul.ecommerce.data.repository.ValueEventResponse
import com.makinul.ecommerce.data.repository.singleValueEvent
import com.makinul.ecommerce.data.repository.valueEventFlow
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import okhttp3.internal.wait

@AndroidEntryPoint
class HomeFragment : Fragment() {


    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        firebaseDatabase = Firebase.database
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val root: View = inflater.inflate(R.layout.fragment_home, container, false)

        val textView: TextView = root.findViewById(R.id.text_home)
        viewModel.text.observe(viewLifecycleOwner, {
            textView.text = it
        })

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        asd()
    }

    private fun asd() {
        val job = SupervisorJob() // Create a scope which will keep reference to its child jobs

        CoroutineScope(Dispatchers.Main + job).launch {
            val response = firebaseDatabase.getReference("products/items").singleValueEvent()

            try {
                response.wait()
            } catch (e: Exception){
                e.printStackTrace()
            }
            Log.v("TAG", "testing")
        }
    }
}