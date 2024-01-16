package com.example.minhaloja.adapters


import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import com.example.minhaloja.R
import com.example.minhaloja.model.Product


class ProductAdapter(
    context: Context,
    private val activity: Activity
) : ArrayAdapter<Product>(context, 0) {

    fun updateData(newList: List<Product>) {
        clear()
        addAll(newList)
        notifyDataSetChanged()

    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val product = getItem(position)
        val view = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.item_grid_inicio, parent, false)

        val imageView: ImageView = view.findViewById(R.id.imageView)
        val textView: TextView = view.findViewById(R.id.textView)

        imageView.setImageResource(product?.image ?: R.drawable.coffee_pot)
        textView.text = product?.txtTitle

        view.setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_navigation_home_to_camisasFragment);

        }

        return view
    }

}

