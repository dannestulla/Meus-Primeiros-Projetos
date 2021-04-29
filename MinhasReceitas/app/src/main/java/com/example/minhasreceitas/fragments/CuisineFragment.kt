package com.example.minhasreceitas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.minhasreceitas.R
import com.example.minhasreceitas.ReceitasViewModel
import com.example.minhasreceitas.databinding.FragmentCuisineBinding
import com.example.minhasreceitas.databinding.FragmentRegisterBinding
import com.example.minhasreceitas.util.ReceitasAdapter


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
        val receitasAdapter = ReceitasAdapter()


        //deve ter alguma forma de fazer isso sem tanta repetição :/

        binding.apply {
            imageView3.setOnClickListener {
                viewModel.getCuisine("japanese")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView4.setOnClickListener {
                viewModel.getCuisine("italian")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView5.setOnClickListener {
                viewModel.getCuisine("vietnamese")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView6.setOnClickListener {
                viewModel.getCuisine("american")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView7.setOnClickListener {
                viewModel.getCuisine("arabic")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView8.setOnClickListener {
                viewModel.getCuisine("chinese")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView9.setOnClickListener {
                viewModel.getCuisine("healthy")
                navController.navigate(R.id.action_cuisineFragment_to_recipeFragment) }
            imageView10.setOnClickListener {
                viewModel.getCuisine("vegan")
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
