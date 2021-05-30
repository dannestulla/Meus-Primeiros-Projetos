package com.example.minhasreceitas.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.example.minhasreceitas.R
import com.example.minhasreceitas.databinding.FragmentStartScreenBinding
import com.example.minhasreceitas.presenter.viewmodel.RecipesListViewModel


class StartScreenFragment : Fragment() {

    private var _binding: FragmentStartScreenBinding? = null
    private val binding get() = _binding!!
    val viewModel by activityViewModels<RecipesListViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartScreenBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)
        binding.apply {
            registerbutton.setOnClickListener { navController.navigate(R.id.action_startScreenFragment_to_registerFragment) }
            alreadyregistered.setOnClickListener { navController.navigate(R.id.action_startScreenFragment_to_loginFragment) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




