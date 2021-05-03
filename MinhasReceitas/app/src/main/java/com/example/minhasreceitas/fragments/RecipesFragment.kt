package com.example.minhasreceitas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.minhasreceitas.R
import com.example.minhasreceitas.ReceitasViewModel
import com.example.minhasreceitas.ReceitasViewModel.Companion.mealsList
import com.example.minhasreceitas.data.network.Meal
import com.example.minhasreceitas.databinding.FragmentRecipeBinding
import com.example.minhasreceitas.util.ReceitasAdapter

class RecipesFragment : Fragment() {
    private var _binding: FragmentRecipeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by activityViewModels<ReceitasViewModel>()
    private val mAdapter = ReceitasAdapter()

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

        binding.imageView15.setOnClickListener { navController.navigate(R.id.action_recipeFragment_to_cuisineFragment) }
        binding.recyclerView.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(context, HORIZONTAL,false)

        }
        viewModel.recyclerViewLiveData.observe(viewLifecycleOwner, { data ->mAdapter.submitList(data)})
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.recyclerViewLiveData.postValue(null)
        _binding = null
    }
}