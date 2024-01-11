package com.example.minhaloja.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.minhaloja.OnFavoritesClickListener
import com.example.minhaloja.R
import com.example.minhaloja.adapters.ItemAdapter
import com.example.minhaloja.databinding.FragmentCamisasBinding
import com.example.minhaloja.model.Item

class CamisasFragment : Fragment(), OnFavoritesClickListener {
    private lateinit var camisasViewModel: CamisasViewModel
    private var _binding: FragmentCamisasBinding? = null
    private val binding get() = _binding!!

    var adapter: ItemAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCamisasBinding.inflate(inflater, container, false)
        val root: View = binding.root

//        val callback = object : OnBackPressedCallback(true) {
//            override fun handleOnBackPressed() {
//                Toast.makeText(requireContext(),"Estou aqui", Toast.LENGTH_LONG).show()
//
//                // O código abaixo será executado quando o botão de voltar for pressionado
//                // Aqui você pode adicionar sua lógica antes de voltar ao fragmento anterior
//                //findNavController().navigateUp()
//            }
//        }
//        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)

        camisasViewModel = ViewModelProvider(requireActivity()).get(CamisasViewModel::class.java)
        // Observa as mudanças na lista de produtos
        camisasViewModel.itemList.observe(viewLifecycleOwner, { itemList ->
            adapter?.updateData(itemList)
        })
        // Inicia o carregamento da lista de produtos
        camisasViewModel.fetchItemList()

        val gridView: GridView = root.findViewById(R.id.gridview)
        adapter = ItemAdapter(requireContext(), this,camisasViewModel)
        adapter?.onFavoritesClickListener = this
        gridView.adapter = adapter

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
//Ao receber o clique no adapter
    override fun onFavoritesClick(item: Item, view: View) {
    camisasViewModel.adicionarRemoverFavorito(item)    // Notificar a ViewModel do clique no botão de favoritos
    Toast.makeText(context, "Adicionado aos Favoritos", Toast.LENGTH_SHORT).show()
    val favoritosList = camisasViewModel.favoritosList.value ?: emptyList()
    adapter?.updateFavoritosList(favoritosList)
    // Atualizar a aparência do botão clicado
    //item.clicado = true
    //
    }

}