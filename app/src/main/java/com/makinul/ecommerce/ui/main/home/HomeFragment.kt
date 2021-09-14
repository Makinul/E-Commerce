package com.makinul.ecommerce.ui.main.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import coil.load
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
        viewModel.subCategories.observe(viewLifecycleOwner, {
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
        if (categories.isEmpty()) {
            prepareCategories()
        }
        prepareCategoryView()

        val testImageView: ImageView = binding.testImageView
        testImageView.load(
            "https://firebasestorage.googleapis.com/v0/b/jolchaya-a5fc3.appspot.com/o/products%2Fsubcategories%2Fjolchaya_icon_jeweller_anklet.png?alt=media&token=b66b147d-d09e-4aac-8c27-b00593d0ef6a"
        )
    }

    private fun prepareCategories() {
        viewModel.getCategories()
    }

    private lateinit var categoryAdapter: CategoryAdapter

    private fun prepareCategoryView() {
        categoryAdapter = CategoryAdapter(categories, object : CategoryAdapter.OnClickListener {
            override fun onItemClick(view: View, position: Int, item: Category) {
                viewModel.getSubCategories(item.id)
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