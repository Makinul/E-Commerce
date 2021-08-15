package com.makinul.ecommerce.ui.settings

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.makinul.ecommerce.R
import com.makinul.ecommerce.databinding.ActivityMainBinding
import com.makinul.ecommerce.databinding.ActivitySetttingsBinding
import com.makinul.ecommerce.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsActivity : AppCompatActivity() {

    private val viewModel: SettingsViewModel by viewModels()
    private lateinit var binding: ActivitySetttingsBinding
    private lateinit var adapter: EmployeeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySetttingsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = EmployeeAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = adapter

//        binding.button.setOnClickListener {
//
//        }

        viewModel.res.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    binding.progress.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    it.data.let { res ->
                        if (res?.status == "success") {
                            res.data?.let { it1 -> adapter.submitList(it1) }
                        } else {
                            Snackbar.make(binding.root, "Status = false", Snackbar.LENGTH_SHORT).show()
                        }
                    }
                }
                Status.LOADING -> {
                    binding.progress.visibility = View.VISIBLE
                    binding.recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    binding.progress.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                    Snackbar.make(binding.root, "Something went wrong", Snackbar.LENGTH_SHORT).show()
                }
            }
        })
    }
}