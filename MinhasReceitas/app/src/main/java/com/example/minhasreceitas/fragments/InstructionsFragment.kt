package com.example.minhasreceitas.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.minhasreceitas.viewmodels.ReceitasViewModel
import com.example.minhasreceitas.viewmodels.ReceitasViewModel.Companion.currentMeal
import com.example.minhasreceitas.databinding.FragmentInstructionsBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class InstructionsFragment : Fragment() {
    private val viewModel by activityViewModels<ReceitasViewModel>()
    private var _binding: FragmentInstructionsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentInstructionsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(IO).launch {
            viewModel.getInstructions(currentMeal[0].idMeal)
        }

        viewModel.descriptionData.apply {
            observe(viewLifecycleOwner, { binding.textView22.text = it[0].strMeal })
            observe(viewLifecycleOwner, { binding.textView27.text = it[0].strInstructions })
            observe(viewLifecycleOwner, { binding.textView25.text = it[0].strArea })
            observe(viewLifecycleOwner, { Picasso.get().load(it[0].strMealThumb).into(binding.recipeimage)})
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        RecipesFragment().mAdapter.submitList(emptyList())
        currentMeal.clear()
        _binding = null
    }

}







