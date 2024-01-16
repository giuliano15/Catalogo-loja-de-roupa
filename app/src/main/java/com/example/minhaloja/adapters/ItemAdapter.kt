


import android.content.Context
import android.content.Intent
import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.minhaloja.R
import com.example.minhaloja.model.Item
import com.example.minhaloja.ui.ProductDetailsActivity
import com.example.minhaloja.ui.home.CamisasViewModel
import com.like.LikeButton
import com.like.OnLikeListener


class ItemAdapter(
    context: Context,
    private val fragment: Fragment,
    private val viewModel: CamisasViewModel
) : ArrayAdapter<Item>(context, 0) {

    //var onFavoritesClickListener: OnFavoritesClickListener? = null

    var favoritosSet: Set<Item> = setOf()
    var favoritosCallback: ((Item) -> Unit)? = null

    fun updateData(newList: List<Item>) {
        clear()
        addAll(newList)
        notifyDataSetChanged()

    }
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val product = getItem(position)
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_grid_product, parent, false)

        val imageView: ImageView = view.findViewById(R.id.imgProduct)
        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
        val txtPrcAntes: TextView = view.findViewById(R.id.txtPrcAntes)
        val txtPrecoAtual: TextView = view.findViewById(R.id.txtPrecoAtual)
        val txtFormPgto: TextView = view.findViewById(R.id.txtFormPgto)

        txtPrcAntes.setPaintFlags(txtPrcAntes.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)

        val likeButton: View = view.findViewById(R.id.likeButtonHeart)
       // val likeButtonFeed: View = view.findViewById(R.id.likeButtonFeed)


        likeButton.setOnClickListener(object : OnLikeListener, View.OnClickListener {
            override fun liked(likeButton: LikeButton) {}
            override fun unLiked(likeButton: LikeButton) {}
            override fun onClick(p0: View?) {
                TODO("Not yet implemented")
            }
        })



//        likeButtonFeed.setOnClickListener(object : OnCheckListener, View.OnClickListener {
//            override fun onChecked(view: ExpressView?) {
//                Log.d(TAG, "Checked")
//                Toast.makeText(context,"Checked",Toast.LENGTH_LONG).show()
//            }
//            override fun onUnChecked(view: ExpressView?) {
//                Log.d(TAG, "Unchecked")
//                Toast.makeText(context,"Unchecked",Toast.LENGTH_LONG).show()
//
//            }


//        btnAddToFavorites.setOnClickListener {
//            product?.let { item ->
//                viewModel.adicionarRemoverFavorito(fragment.requireContext(),item)
//                favoritosCallback?.invoke(item)
//                // ... restante do código
//                item.clicado = !item.clicado
//
//                // Atualiza a imagem do botão de favoritos com base na propriedade clicado
//                btnAddToFavorites.setImageResource(
//                    if (item.clicado) R.drawable.ic_favorite_red_24dp
//                    else R.drawable.favorite_border_24
//                )
//            }
//        }


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

