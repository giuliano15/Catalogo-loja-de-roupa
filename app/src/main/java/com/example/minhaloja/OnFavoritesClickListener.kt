package com.example.minhaloja

import android.view.View
import com.example.minhaloja.model.Item

interface OnFavoritesClickListener {
    fun onFavoritesClick(item: Item, view: View)
}