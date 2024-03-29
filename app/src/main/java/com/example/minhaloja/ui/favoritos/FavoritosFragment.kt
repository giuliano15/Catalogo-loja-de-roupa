package com.example.minhaloja.ui.favoritos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minhaloja.R
import com.example.minhaloja.adapters.FavoritosAdapter
import com.example.minhaloja.databinding.FragmentFavoritosBinding
import com.example.minhaloja.ui.home.CamisasViewModel

class FavoritosFragment : Fragment() {

    private var _binding: FragmentFavoritosBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CamisasViewModel
    private lateinit var adapter: FavoritosAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_favoritos, container, false)


        //val viewModelFactory = CamisasViewModelFactory(requireActivity().application)
       //viewModel = ViewModelProvider(this, viewModelFactory).get(CamisasViewModel::class.java)
        // Inicializa o ViewModel
        viewModel = ViewModelProvider(requireActivity()).get(CamisasViewModel::class.java)

        // Inicializa o RecyclerView
        recyclerView = root.findViewById(R.id.recyclerViewFavoritos)

        // Inicializa o Adapter
        adapter = FavoritosAdapter(mutableListOf())

        // Configura o RecyclerView com o LinearLayoutManager e associa o Adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        // Observar as mudanças na lista de favoritos
        viewModel.favoritosList.observe(viewLifecycleOwner, { listaFavoritos ->
            // Atualizar o adapter com a nova lista de favoritos
            val teste = adapter.updateData(listaFavoritos)
        })

        return root
    }

}

