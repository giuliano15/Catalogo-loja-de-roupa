package com.example.minhaloja.adapters


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.minhaloja.OnFavoritesClickListener
import com.example.minhaloja.ProductDetailsActivity
import com.example.minhaloja.R
import com.example.minhaloja.model.Item
import com.example.minhaloja.ui.home.CamisasViewModel


class ItemAdapter(
    context: Context,
    private val fragment: Fragment,
    private val viewModel: CamisasViewModel
) : ArrayAdapter<Item>(context, 0) {

    var onFavoritesClickListener: OnFavoritesClickListener? = null

    var favoritosSet: Set<Item> = setOf()

    fun updateData(newList: List<Item>) {
        clear()
        addAll(newList)
        notifyDataSetChanged()

//        activity?.runOnUiThread {
//            val toastMessage = buildToastMessage()
//            Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT).show()
//        }
    }

//    private fun buildToastMessage(): String {
//        val messageBuilder = StringBuilder("Lista Atualizada:\n")
//        for (i in 0 until count) {
//            val product = getItem(i)
//            messageBuilder.append("${product?.name} - ${product?.description}\n")
//        }
//        return messageBuilder.toString()
//    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val product = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_grid_product, parent, false)

        val imageView: ImageView = view.findViewById(R.id.imgProduct)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtPrcAntes: TextView = view.findViewById(R.id.txtPrcAntes)
        val txtPrecoAtual: TextView = view.findViewById(R.id.txtPrecoAtual)
        val txtFormPgto: TextView = view.findViewById(R.id.txtFormPgto)

        txtPrcAntes.setPaintFlags(txtPrcAntes.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

        val btnAddToFavorites: ImageButton = view.findViewById(R.id.btnAddToFavorites)
//        btnAddToFavorites.setOnClickListener {
//            product?.let { item ->
//                if (fragment is OnFavoritesClickListener) {
//                    (fragment as OnFavoritesClickListener).onFavoritesClick(item)
//                }
//
//                val isFavoritoAfterClick = favoritosSet.contains(item)
//                btnAddToFavorites.setImageResource(if (!isFavoritoAfterClick) R.drawable.ic_favorite_red_24dp else R.drawable.favorite_border_24)
//            }
//        }

//        btnAddToFavorites.setImageResource(
//            if (product?.clicado == true) R.drawable.ic_favorite_red_24dp
//            else R.drawable.favorite_border_24
//        )

        btnAddToFavorites.setOnClickListener {
            product?.let { item ->
                viewModel.adicionarRemoverFavorito(item)
                onFavoritesClickListener?.onFavoritesClick(item, it)
                // ... restante do código
                item.clicado = !item.clicado

                // Atualiza a imagem do botão de favoritos com base na propriedade clicado
                btnAddToFavorites.setImageResource(
                    if (item.clicado) R.drawable.ic_favorite_red_24dp
                    else R.drawable.favorite_border_24
                )
            }
        }


        imageView.setImageResource(product?.image ?: R.drawable.coffee_pot)
        txtTitle.text = product?.txtTitle
        txtPrcAntes.text = product?.precoDesc
        txtPrecoAtual.text = product?.price
        txtFormPgto.text = product?.formPgto

        view.setOnClickListener {
            val intent = Intent(context, ProductDetailsActivity::class.java)
            intent.putExtra("name", product?.txtTitle ?: "")
            intent.putExtra("tamanho", product?.tamanho?: "")
            intent.putExtra("image", product?.image ?: R.drawable.coffee_pot)
            intent.putExtra("formPgto",product?.formPgto?: "")
            intent.putExtra("price",product?.price?: "")
            context.startActivity(intent)
        }

        return view
    }

    fun updateFavoritosList(newList: List<Item>) {
        // Atualiza a lista de favoritos
        favoritosSet = newList.toSet()

        // Notifica o adapter sobre as alterações
        notifyDataSetChanged()
    }


}

