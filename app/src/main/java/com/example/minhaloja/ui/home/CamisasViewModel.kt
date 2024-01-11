package com.example.minhaloja.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.minhaloja.R
import com.example.minhaloja.ViewModelEventListener
import com.example.minhaloja.model.Item
import com.example.minhaloja.repository.ListCamisasRepository

class CamisasViewModel : ViewModel() {
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
    fun adicionarRemoverFavorito(item: Item) {
        val listaFavoritos = _favoritosList.value ?: mutableListOf()
        if (!listaFavoritos.contains(item)) {
            listaFavoritos.add(item)
        } else {
            //listaFavoritos.add(item)
        }
        _favoritosList.value = listaFavoritos
    }

//    fun adicionarAoFavoritos(item: Item) {
//        val listaFavoritos = _favoritosList.value ?: mutableListOf()
//       // listaFavoritos.add(item)
//        _favoritosList.value = listaFavoritos
//    }
}
