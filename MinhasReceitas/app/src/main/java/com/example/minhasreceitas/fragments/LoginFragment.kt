package com.example.minhasreceitas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.minhasreceitas.R
import com.example.minhasreceitas.ReceitasViewModel
import com.example.minhasreceitas.databinding.FragmentLoginBinding
import com.example.minhasreceitas.databinding.FragmentRegisterBinding


class LoginFragment : Fragment() {

    val viewModel by activityViewModels<ReceitasViewModel>()

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        binding.apply {
            textView6.setOnClickListener { navController.navigate(R.id.action_loginFragment_to_registerFragment) }
            button2?.setOnClickListener { navController.navigate(R.id.action_loginFragment_to_cuisineFragment) }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}