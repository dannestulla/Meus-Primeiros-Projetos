package com.example.minhasreceitas.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.minhasreceitas.ReceitasViewModel
import com.example.minhasreceitas.ReceitasViewModel.Companion.currentMeal
import com.example.minhasreceitas.databinding.FragmentDescriptionBinding
import com.squareup.picasso.Picasso
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DescriptionFragment : Fragment() {
    val viewModel by activityViewModels<ReceitasViewModel>()

    private var _binding: FragmentDescriptionBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        CoroutineScope(IO).launch {
            viewModel.getInstructions(currentMeal[0].idMeal)
           }



    viewModel.descriptionData.apply{
            observe(viewLifecycleOwner, {binding.textView22.text = it[0].strMeal}) //name
            observe(viewLifecycleOwner, {binding.textView27.text = it[0].strInstructions}) //description
            observe(viewLifecycleOwner, {binding.textView25.text = it[0].strArea}) // area



    }}
    override fun onDestroyView() {
        super.onDestroyView()

        currentMeal.clear()
        _binding = null
    }

}