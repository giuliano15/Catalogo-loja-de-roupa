package com.example.minhaloja.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.minhaloja.R
import com.example.minhaloja.adapters.ProductAdapter
import com.example.minhaloja.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    var adapter: ProductAdapter? = null
    private lateinit var productViewModel: ProductViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        // Observa as mudanças na lista de produtos
        productViewModel.productList.observe(viewLifecycleOwner, { productList ->
            adapter?.updateData(productList)
        })
        // Inicia o carregamento da lista de produtos
        productViewModel.fetchProductList()

        val gridView: GridView = root.findViewById(R.id.gridview)
        adapter = ProductAdapter(requireContext(), requireActivity())
        gridView.adapter = adapter


        //val textView: TextView = binding.textHome
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            // textView.text = it
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




