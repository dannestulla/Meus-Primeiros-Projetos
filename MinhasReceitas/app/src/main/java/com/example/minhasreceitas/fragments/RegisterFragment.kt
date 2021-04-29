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
import com.example.minhasreceitas.databinding.FragmentRegisterBinding
import com.example.minhasreceitas.databinding.FragmentStartScreenBinding


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    val viewModel by activityViewModels<ReceitasViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        binding.apply {
            register.setOnClickListener { navController.navigate(R.id.action_registerFragment_to_cuisineFragment) }
            textView4.setOnClickListener { navController.navigate(R.id.action_registerFragment_to_loginFragment)}
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}