package com.example.minhaloja.ui.favoritos

import SwipeToDeleteCallback
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.minhaloja.CamisasViewModelFactory
import com.example.minhaloja.R
import com.example.minhaloja.adapters.FavoritosAdapter
import com.example.minhaloja.databinding.FragmentFavoritosBinding
import com.example.minhaloja.repository.FavoritosRepository
import com.example.minhaloja.ui.home.CamisasViewModel

class DashboardFragment : Fragment() {

    private var _binding: FragmentFavoritosBinding? = null

    private lateinit var viewModel: CamisasViewModel
    private lateinit var adapter: FavoritosAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var favoritosRepository: FavoritosRepository

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val dashboardViewModel =
//            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentFavoritosBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val textView: Button = binding.textDashboard
//        dashboardViewModel.text.observe(viewLifecycleOwner) {
//            textView.setOnClickListener {
//                Navigation.findNavController(root).navigate(R.id.action_navigation_dashboard_to_novoFragment);
//            }
//        }

        favoritosRepository = FavoritosRepository(requireContext())
        val factory = CamisasViewModelFactory(favoritosRepository)
        viewModel = ViewModelProvider(this, factory).get(CamisasViewModel::class.java)

       // viewModel = ViewModelProvider(requireActivity()).get(CamisasViewModel::class.java)

        // Inicializa o RecyclerView
        recyclerView = root.findViewById(R.id.recyclerViewFavoritos)

        // Inicializa o Adapter
        adapter = FavoritosAdapter(mutableListOf())


        // Configura o RecyclerView com o LinearLayoutManager e associa o Adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        viewModel.carregarFavoritos()
        // Observar as mudanças na lista de favoritos
        viewModel.favoritosList.observe(viewLifecycleOwner, { listaFavoritos ->
            // Atualizar o adapter com a nova lista de favoritos
             adapter.updateData(listaFavoritos)
        })

        val itemTouchHelper = ItemTouchHelper(SwipeToDeleteCallback(adapter,favoritosRepository))
        itemTouchHelper.attachToRecyclerView(recyclerView)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}