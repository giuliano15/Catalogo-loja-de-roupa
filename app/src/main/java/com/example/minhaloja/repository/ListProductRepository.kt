package com.example.minhaLoja.repository

import com.example.minhaloja.R
import com.example.minhaloja.model.Product

object ListProductRepository {

    fun createProductList(): List<Product> {
        val productList = ArrayList<Product>()
        productList.add(Product("Camisas",R.drawable.camisa_listrada, "P M G", "5x no cartão ou direto no Pix", "60"))
        productList.add(Product("Calças",  R.drawable.calca,"P M G","5x no cartão ou direto no Pix", "150"))
        productList.add(Product("Bones",R.drawable.bonee,"P M G", "5x no cartão ou direto no Pix",  "70"))
        productList.add(Product("relógio", R.drawable.relogioo, "P M G","5x no cartão ou direto no Pix", "500"))
        productList.add(Product("cordão", R.drawable.cordaoo, "P M G","5x no cartão ou direto no Pix", "400"))
        productList.add(Product("Tênis", R.drawable.teniss,"P M G","5x no cartão ou direto no Pix",  "350"))

        // ... Adicione outros itens de comida
        return productList
    }
}

