package com.example.minhaloja.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.minhaloja.ProductDetailsActivity
import com.example.minhaloja.R
import com.example.minhaloja.model.Item

class FavoritosAdapter(var listaFavoritos: MutableList<Item> = mutableListOf()) :
    RecyclerView.Adapter<FavoritosAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgProduct: ImageView = itemView.findViewById(R.id.imgProduct)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val txtPrecoAtual: TextView = itemView.findViewById(R.id.txtPrecoAtual)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_favorito, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listaFavoritos[position]

        // Configurar os elementos do layout com os dados do item
        item.image?.let { holder.imgProduct.setImageResource(it) }
        holder.txtTitle.text = item.txtTitle
        holder.txtPrecoAtual.text = item.price

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, ProductDetailsActivity::class.java)
            intent.putExtra("name", item?.txtTitle ?: "")
            intent.putExtra("tamanho", item?.tamanho?: "")
            intent.putExtra("image", item?.image ?: R.drawable.coffee_pot)
            intent.putExtra("formPgto",item?.formPgto?: "")
            intent.putExtra("price",item?.price?: "")
            holder.itemView.context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return listaFavoritos.size
    }


    fun updateData(newList: MutableList<Item>) {
        listaFavoritos.clear()
        listaFavoritos.addAll(newList)
        notifyDataSetChanged()
    }

    fun getListFavoritos(): List<Item> {
        return listaFavoritos
    }

}
