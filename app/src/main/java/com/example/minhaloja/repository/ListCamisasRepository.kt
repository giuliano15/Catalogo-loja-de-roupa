package com.example.minhaloja.repository

import com.example.minhaloja.R
import com.example.minhaloja.model.Item

object ListCamisasRepository {

    fun createItemList(): List<Item>{
        val itemList = ArrayList<Item>()
        itemList.add(Item("Camisa listrada floral",R.drawable.camisa_listrada_respect,"P M G","R$ 79,00", "5x no cartão ou direto no Pix", "R$ 60,00",false))
        itemList.add(Item("Camisa polo branca",R.drawable.camisa_polo,"P M G","R$ 99,00","5x no cartão ou direto no Pix","R$ 80,00",false))
        itemList.add(Item("Camiseta polo", R.drawable.camiseta_polo_ocasional,"P M G","R$ 89,00","5x no cartão ou direto no Pix", "R$ 59,00",false))
        itemList.add(Item("Camisa chicago", R.drawable.camisa_chicago,"P M G","R$ 99,00","5x no cartão ou direto no Pix","R$ 89,00",false))
        itemList.add(Item("Camisa floral", R.drawable.camisa_impressao_floral,"P M G","R$ 75,00","5x no cartão ou direto no Pix", "R$ 55,00",false))
        itemList.add(Item("Camisa polo azul e branca", R.drawable.camisa_polo_azul_branca,"P M G","R$ 129,00","5x no cartão ou direto no Pix","R$ 99,00",false))
        itemList.add(Item("Camisa praia desenho", R.drawable.camisa_praia_desenho_animados,"P M G","R$ 99,00","5x no cartão ou direto no Pix","R$ 79,00",false))
        itemList.add(Item("Conjunto camisa e bermuda branca", R.drawable.conjunto_camisa_bermuda_branca,"P M G","R$ 159,00","5x no cartão ou direto no Pix","R$ 129,00",false))
        itemList.add(Item("Camisa dragoon bool",R.drawable.camisa_dragon_bool,"R$ 79,00","P M G","5x no cartão ou direto no Pix","R$ 59,00",false))
        itemList.add(Item("Conjunto camisas", R.drawable.camisas_diversas,"P M G","R$ 125,00","5x no cartão ou direto no Pix","R$ 99,00",false))

        return itemList
    }
}
