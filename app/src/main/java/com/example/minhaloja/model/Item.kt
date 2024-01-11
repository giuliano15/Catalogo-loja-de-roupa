package com.example.minhaloja.model

class Item {
    var txtTitle: String? = null
    var image: Int? = null
    var tamanho: String? = null
    var precoDesc: String? = null
    var formPgto: String? = null
    var price: String? = null
    var clicado: Boolean = false

    constructor(name: String?, image: Int?,tamanho: String?, precoDesc: String?, formPgto: String?, price: String?, ciclado: Boolean) {

        this.txtTitle = name
        this.image = image
        this.tamanho = tamanho
        this.precoDesc = precoDesc
        this.formPgto = formPgto
        this.price = price
        this.clicado = clicado
    }
}


