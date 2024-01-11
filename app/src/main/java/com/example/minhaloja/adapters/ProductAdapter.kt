package com.example.minhaloja.adapters


import android.app.Activity
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.minhaloja.ProductDetailsActivity
import com.example.minhaloja.R
import com.example.minhaloja.model.Item
import com.example.minhaloja.model.Product


class ProductAdapter(
    context: Context,
    private val activity: Activity
) : ArrayAdapter<Product>(context, 0) {



    fun updateData(newList: List<Product>) {
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
        val view = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_grid_inicio, parent, false)

//        val imageView: ImageView = view.findViewById(R.id.imgProduct)
//        val txtTitle: TextView = view.findViewById(R.id.txtTitle)
//        val txtPrcAntes: TextView = view.findViewById(R.id.txtPrcAntes)
//        val txtPrecoAtual: TextView = view.findViewById(R.id.txtPrecoAtual)
//        val txtFormPgto: TextView = view.findViewById(R.id.txtFormPgto)


         val imageView: ImageView = view.findViewById(R.id.imageView)
        val textView: TextView = view.findViewById(R.id.textView)

        imageView.setImageResource(product?.image ?: R.drawable.coffee_pot)
        textView.text = product?.txtTitle
//        txtPrcAntes.text = product?.tamanho
//        txtPrecoAtual.text = product?.price
//        txtFormPgto.text = product?.formPgto

        view.setOnClickListener {
//            val intent = Intent(context, ProductDetailsActivity::class.java)
//            intent.putExtra("name", product?.txtTitle ?: "")
//            intent.putExtra("tamanho", product?.tamanho?: "")
//            intent.putExtra("image", product?.image ?: R.drawable.coffee_pot)
//            intent.putExtra("formPgto",product?.formPgto?: "")
//            intent.putExtra("price",product?.price?: "")
//            context.startActivity(intent)
            Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_camisasFragment);
           // Navigation.findNavController(view).navigate(R.id.action_navigation_home_to_novoFragment);

        }

        return view
    }

}

