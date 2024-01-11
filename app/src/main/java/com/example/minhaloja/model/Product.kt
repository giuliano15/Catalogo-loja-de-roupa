package com.example.minhaloja.model

import android.widget.ImageView
import android.widget.TextView
import com.example.minhaloja.R

class Product {
    var txtTitle : String? = null
    var image : Int? = null
    var tamanho: String? = null
    var formPgto: String? = null
    var price: String? = null

    constructor(name: String?,image: Int?,tamanho: String?,formPgto: String?, price: String?) {
        this.txtTitle = name
        this.image = image
        this.tamanho = tamanho
        this.formPgto = formPgto
        this.price = price

    }
}

