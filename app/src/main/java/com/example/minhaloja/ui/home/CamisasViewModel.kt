package com.example.minhaloja.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minhaloja.model.Item
import com.example.minhaloja.repository.FavoritosRepository
import com.example.minhaloja.repository.ListCamisasRepository


class CamisasViewModel (private val favoritosRepository: FavoritosRepository) : ViewModel() {
    // Instância do repositório responsável por fornecer os dados
    private val repositoryItem = ListCamisasRepository

    // Lista mutável de itens que será observada pela UI
    private val _itemList = MutableLiveData<List<Item>>()

    // Lista de itens acessível publicamente apenas como LiveData (somente leitura)
    val itemList: LiveData<List<Item>> get() = _itemList

    // Função para buscar a lista de itens do repositório e atualizar _itemList
    fun fetchItemList() {
        _itemList.value = repositoryItem.createItemList()
    }

    private val _favoritosList = MutableLiveData<MutableList<Item>>()
    val favoritosList: LiveData<MutableList<Item>> get() = _favoritosList


    init {
        _favoritosList.value = mutableListOf()
    }


    // Método para adicionar ou remover itens da lista de favoritos
    fun adicionarRemoverFavorito( context: Context, item: Item) {
        val listaFavoritos = _favoritosList.value?.toMutableSet() ?: mutableSetOf()

        if (!listaFavoritos.contains(item)) {
            // Se o item não estiver na lista, adicione-o
            listaFavoritos.add(item)

            // Atualize o repositório e o LiveData
            favoritosRepository.salvarFavoritos(listaFavoritos.toMutableList())
            _favoritosList.value = listaFavoritos.toMutableList()
        }    }

    fun carregarFavoritos() {
        val favoritos = favoritosRepository.obterFavoritos().toMutableList()
        _favoritosList.value = favoritos
    }

}
