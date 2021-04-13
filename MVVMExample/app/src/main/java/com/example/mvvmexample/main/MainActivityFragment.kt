package com.ericdecanini.basicmvvm.activity.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.mvvmexample.databinding.FragmentMainBinding
import com.example.mvvmexample.main.MainActivityViewModel


class MainActivityFragment: Fragment() {

    private var _binding : FragmentMainBinding? =null
    private val binding get() = _binding!! //bypassando o not null check do Kotlin

    private val viewModel: MainActivityViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeDogBreed()
    }

    private fun setupClickListeners() {
        binding.dogbreedButton.setOnClickListener { viewModel.getRandomDogBreed() } //vai adicionar o valor à lista mutável
    }

    private fun observeDogBreed() {
        viewModel.dogBreedLiveData.observe(viewLifecycleOwner, Observer { breed -> //vai começar a observer o array MutableLiveData
            binding.dogbreedTextview.text = breed
        })
    }
}