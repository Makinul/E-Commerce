package com.makinul.ecommerce.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.makinul.ecommerce.BaseFragment
import com.makinul.ecommerce.data.model.Category
import com.makinul.ecommerce.databinding.FragmentHomeBinding
import com.makinul.ecommerce.util.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModels()

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.categories.observe(viewLifecycleOwner, {
            when (it.status) {
                Status.LOCAL -> {
                    categories.clear()
                    it.data?.let { data ->
                        categories.addAll(data)
                    }
                }
                Status.SUCCESS -> {
                    categories.clear()
                    it.data?.let { data ->
                        categories.addAll(data)
                    }
                }
                Status.LOADING -> {
                    binding.statusLay.statusLay.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    showToast(it.message)
                }
            }
        })
        if (categories.isEmpty()) {
            prepareCategories()
        }
        prepareCategoryView()
    }

    private fun prepareCategories() {
        viewModel.getCategories()
    }

    private lateinit var categoryAdapter: CategoryAdapter

    private fun prepareCategoryView() {
        categoryAdapter = CategoryAdapter(categories, object : CategoryAdapter.OnClickListener {
            override fun onItemClick(view: View, position: Int, item: Category) {
                TODO("Not yet implemented")
            }
        })
        val linearLayoutManager = LinearLayoutManager(requireActivity())
        val categoryRecyclerView: RecyclerView = binding.categoryRecyclerView
        categoryRecyclerView.layoutManager = linearLayoutManager
        categoryRecyclerView.adapter = categoryAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        val categories = ArrayList<Category>()
    }
}