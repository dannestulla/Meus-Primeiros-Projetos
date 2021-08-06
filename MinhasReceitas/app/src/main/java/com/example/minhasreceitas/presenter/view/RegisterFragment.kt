package com.example.minhasreceitas.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.minhasreceitas.R
import com.example.minhasreceitas.databinding.FragmentRegisterBinding
import com.example.minhasreceitas.presenter.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!
    val viewModel by activityViewModels<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.register.setOnClickListener {
            viewModel.createAccount(
                binding.editTextEmail.text.toString(),
                binding.passwordfield.text.toString(),
                binding.validadepassword.text.toString()
            )
        }

        binding.alreadyregistered.setOnClickListener { findNavController().navigate(R.id.action_registerFragment_to_loginFragment) }
        viewModel.fragmentDestination.observe(viewLifecycleOwner, {
            when (it) {
                "registerToLogin" -> findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
                "registerToCuisine" -> findNavController().navigate(R.id.action_registerFragment_to_cuisineFragment)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}