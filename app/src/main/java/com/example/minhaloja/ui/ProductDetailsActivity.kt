package com.example.minhaloja.ui

import android.content.ContentResolver
import android.content.ContentValues
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.minhaloja.R
import java.io.ByteArrayOutputStream
import java.io.IOException

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        val imagem: ImageView = findViewById(R.id.imageView2)
        val txtTitle: TextView = findViewById(R.id.txtTitle)
        val txtTamanho: TextView = findViewById(R.id.txtPrcAntes)
        val textPrice: TextView = findViewById(R.id.txtPrecoAtual)
        val textFormPgto: TextView = findViewById(R.id.txtFormPgto)
        val btnFazerPedido: Button = findViewById(R.id.btnFazerPedido)

        val bundle = intent.extras
        imagem.setImageResource(bundle!!.getInt("image"))
        txtTitle.text = bundle!!.getString("name")

        val tamanho = bundle.getString("tamanho")
        txtTamanho.text = "Tam: $tamanho"

        val preco = bundle.getString("price")
        textPrice.text = "R$ $preco"

        textFormPgto.text = bundle!!.getString("formPgto")

        btnFazerPedido.setOnClickListener {
            val originalBitmap = BitmapFactory.decodeResource(resources, bundle!!.getInt("image"))
            val uri = getUriToBitmap(originalBitmap, contentResolver)
            shareOrderDetails(txtTitle.text.toString(), uri)
        }
    }

    private fun shareOrderDetails(itemName: String, imageUri: Uri?) {
        // Criar uma Intent de compartilhamento
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"

        // Adicionar o texto ao compartilhamento
        intent.putExtra(Intent.EXTRA_TEXT, "Olá, gostei do produto $itemName. Poderia me enviar mais detalhes?")

        // Adicionar a imagem ao compartilhamento
        imageUri?.let {
            intent.putExtra(Intent.EXTRA_STREAM, it)
            intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }

        // Iniciar a atividade de compartilhamento
        startActivity(Intent.createChooser(intent, "Compartilhar via"))
    }

    private fun getUriToBitmap(bitmap: Bitmap, contentResolver: ContentResolver): Uri? {
        try {
            val bytes = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)

            val values = ContentValues().apply {
                put(MediaStore.Images.Media.TITLE, "temp_image")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            }

            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            uri?.let {
                contentResolver.openOutputStream(it)?.use { outputStream ->
                    outputStream.write(bytes.toByteArray())
                }
            }
            return uri
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
}

/*

class ProductDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_details)
        val imagem: ImageView = findViewById(R.id.imageView2)
        val txtTitle: TextView = findViewById(R.id.txtTitle)
        val txtTamanho: TextView = findViewById(R.id.txtPrcAntes)
        val textPrice: TextView = findViewById(R.id.txtPrecoAtual)
        val textFormPgto: TextView = findViewById(R.id.txtFormPgto)
        val btnFazerPedido: Button = findViewById(R.id.btnFazerPedido)

        val bundle = intent.extras
        imagem.setImageResource(bundle!!.getInt("image"))
        txtTitle.text = bundle!!.getString("name")

        val tamanho = bundle.getString("tamanho")
        txtTamanho.text = "Tam: $tamanho"

        val preco = bundle.getString("price")
        textPrice.text = "R$ $preco"

        textFormPgto.text = bundle!!.getString("formPgto")

        btnFazerPedido.setOnClickListener { shareOrderDetails(txtTitle.text.toString(), bundle!!.getInt("image")) }
    }

    private fun shareOrderDetails(itemName: String, imageResId: Int) {
        // Carregar a imagem original com opções de configuração
        val options = BitmapFactory.Options()
        options.inPreferredConfig = Bitmap.Config.ARGB_8888 // Configuração sem compressão
        val originalBitmap = BitmapFactory.decodeResource(resources, imageResId, options)

        // Criar uma Intent de compartilhamento
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "image/*"

        // Adicionar o texto ao compartilhamento
        intent.putExtra(Intent.EXTRA_TEXT, "Olá, gostei do produto $itemName. Poderia me enviar mais detalhes?")

        // Adicionar a imagem ao compartilhamento
        intent.putExtra(Intent.EXTRA_STREAM, getUriToBitmap(originalBitmap))
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)

        // Iniciar a atividade de compartilhamento
        startActivity(Intent.createChooser(intent, "Compartilhar via"))
    }

    private fun getUriToBitmap(bitmap: Bitmap): Uri {
        val bytes = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, bytes)
        val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "Title", null)
        return Uri.parse(path)
    }
}
*/