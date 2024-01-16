package com.example.minhaloja.model

import java.util.Objects

class Item {
    var txtTitle: String? = null
    var image: Int? = null
    var tamanho: String? = null
    var precoDesc: String? = null
    var formPgto: String? = null
    var price: String? = null
    var clicado: Boolean = false

    constructor(
        name: String?,
        image: Int?,
        tamanho: String?,
        precoDesc: String?,
        formPgto: String?,
        price: String?,
        clicado: Boolean
    ) {
        this.txtTitle = name
        this.image = image
        this.tamanho = tamanho
        this.precoDesc = precoDesc
        this.formPgto = formPgto
        this.price = price
        this.clicado = clicado
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false

        val item = other as Item

        return txtTitle == item.txtTitle &&
                image == item.image &&
                tamanho == item.tamanho &&
                precoDesc == item.precoDesc &&
                formPgto == item.formPgto &&
                price == item.price &&
                clicado == item.clicado
    }

    override fun hashCode(): Int {
        return Objects.hash(
            txtTitle,
            image,
            tamanho,
            precoDesc,
            formPgto,
            price,
            clicado
        )
    }
}
