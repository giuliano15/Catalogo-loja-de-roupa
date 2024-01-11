package com.example.minhaloja.ui.home


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minhaLoja.repository.ListProductRepository
import com.example.minhaloja.model.Product

class ProductViewModel : ViewModel() {

    private val repository = ListProductRepository
    private val _productList = MutableLiveData<List<Product>>()

    val productList: LiveData<List<Product>> get() = _productList

    fun fetchProductList() {
        _productList.value = repository.createProductList()
    }
}
