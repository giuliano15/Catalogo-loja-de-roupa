package com.example.minhaloja.ui.home

import ItemAdapter
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.minhaloja.CamisasViewModelFactory
import com.example.minhaloja.R
import com.example.minhaloja.databinding.FragmentCamisasBinding
import com.example.minhaloja.repository.FavoritosRepository

class CamisasFragment : Fragment() {
    private lateinit var camisasViewModel: CamisasViewModel
    private var _binding: FragmentCamisasBinding? = null
    private val binding get() = _binding!!

    var adapter: ItemAdapter? = null

    private lateinit var favoritosRepository: FavoritosRepository

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCamisasBinding.inflate(inflater, container, false)
        val root: View = binding.root

        favoritosRepository = FavoritosRepository(requireContext())
        val factory = CamisasViewModelFactory(favoritosRepository)
        camisasViewModel = ViewModelProvider(this, factory).get(CamisasViewModel::class.java)
        //camisasViewModel = ViewModelProvider(requireActivity()).get(CamisasViewModel::class.java)
        // Observa as mudanças na lista de produtos
        camisasViewModel.itemList.observe(viewLifecycleOwner, { itemList ->
            adapter?.updateData(itemList)
        })
        // Inicia o carregamento da lista de produtos
        camisasViewModel.fetchItemList()

        val gridView: GridView = root.findViewById(R.id.gridview)
        adapter = ItemAdapter(requireContext(), this,camisasViewModel)
        gridView.adapter = adapter

       // callBack do clique do botão favoritos
        adapter?.favoritosCallback = { item ->
            // Lógica de favoritar na Fragment
            camisasViewModel.adicionarRemoverFavorito(requireContext(), item)
            Toast.makeText(context, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show()
            val favoritosList = camisasViewModel.favoritosList.value ?: emptyList()
            adapter?.updateFavoritosList(favoritosList)

        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}