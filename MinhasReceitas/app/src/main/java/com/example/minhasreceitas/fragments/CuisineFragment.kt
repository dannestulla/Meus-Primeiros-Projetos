package com.example.minhasreceitas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.minhasreceitas.R
import com.example.minhasreceitas.viewmodels.ReceitasViewModel
import com.example.minhasreceitas.databinding.FragmentCuisineBinding


class CuisineFragment : Fragment() {
    val viewModel by activityViewModels<ReceitasViewModel>()
    private var _binding: FragmentCuisineBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentCuisineBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)


        //deve ter alguma forma de fazer isso sem tanta repetição :/

        binding.apply {
            imageView3.setOnClickListener {
                viewModel.databaseOrAPI("japanese")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView4.setOnClickListener {
                viewModel.databaseOrAPI("italian")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView5.setOnClickListener {
                viewModel.databaseOrAPI("vietnamese")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView6.setOnClickListener {
                viewModel.databaseOrAPI("american")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView8.setOnClickListener {
                viewModel.databaseOrAPI("chinese")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView11.setOnClickListener {
                navController.navigate(R.id.action_cuisineFragment_to_loginFragment)
        }

    }


}
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }}
