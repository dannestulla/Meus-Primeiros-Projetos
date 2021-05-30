package com.example.minhasreceitas.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.VERTICAL
import com.example.minhasreceitas.R
import com.example.minhasreceitas.databinding.FragmentRecipeBinding
import com.example.minhasreceitas.presenter.viewmodel.RecipesListViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class RecipesListFragment : Fragment() {
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<RecipesListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecipeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        binding.backbuttonrecipes.setOnClickListener {
            navController.navigate(R.id.action_recipeFragment_to_cuisineFragment)
        }
        binding.recyclerView.apply {
            adapter = viewModel.mAdapter
            layoutManager = LinearLayoutManager(context, VERTICAL, false)
        }
        //TOGGLING FAVOURITE LIST
        binding.imageView7.setOnClickListener {
            val listFavouritesButtonON = viewModel.favButtonRecipeList
            if (!listFavouritesButtonON) {
                CoroutineScope(IO).launch { viewModel.turnFavouritesListON() }
                it.setBackgroundResource(R.drawable.favoriteon)
            } else {
                CoroutineScope(IO).launch { viewModel.turnFavouritesListOFF() }
                it.setBackgroundResource(R.drawable.favoriteoff)
            }
        }
        viewModel. recyclerViewLiveData.observe(viewLifecycleOwner,
            { viewModel.mAdapter.submitList(it) })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        //viewModel.mAdapter.submitList(emptyList())
        viewModel.recyclerViewLiveData = MutableLiveData()
        _binding = null
    }
}