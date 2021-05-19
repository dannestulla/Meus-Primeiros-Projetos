package com.example.minhasreceitas.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.minhasreceitas.R
import com.example.minhasreceitas.databinding.FragmentCuisineBinding
import com.example.minhasreceitas.viewmodel.RecipesListViewModel

class CuisineFragment : Fragment() {
    val viewModel by activityViewModels<RecipesListViewModel>()
    private var _binding: FragmentCuisineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCuisineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        binding.apply {
            japanese.setOnClickListener {
                viewModel.mAdapter.submitList(emptyList())
                viewModel.databaseOrAPI("japanese")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment)
            }
            italian.setOnClickListener {
                viewModel.mAdapter.submitList(emptyList())
                viewModel.databaseOrAPI("italian")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment)
            }
            vietnamese.setOnClickListener {
                viewModel.mAdapter.submitList(emptyList())
                viewModel.databaseOrAPI("vietnamese")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment)
            }
            american.setOnClickListener {
                viewModel.mAdapter.submitList(emptyList())
                viewModel.databaseOrAPI("american")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment)
            }
            chinese.setOnClickListener {
                viewModel.mAdapter.submitList(emptyList())
                viewModel.databaseOrAPI("chinese")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment)
            }
            backbutton.setOnClickListener {
                viewModel.mAdapter.submitList(emptyList())
                navController.navigate(R.id.action_cuisineFragment_to_loginFragment)
            }

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
