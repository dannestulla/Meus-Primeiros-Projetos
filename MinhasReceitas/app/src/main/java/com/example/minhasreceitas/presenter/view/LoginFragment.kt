package com.example.minhasreceitas.presenter.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.minhasreceitas.R
import com.example.minhasreceitas.databinding.FragmentLoginBinding
import com.example.minhasreceitas.presenter.viewmodel.AuthViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


class LoginFragment : Fragment() {
    val viewModel by activityViewModels<AuthViewModel>()
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        viewModel.auth = FirebaseAuth.getInstance()
        viewModel.auth = Firebase.auth
        binding.apply {
            needtoregister.setOnClickListener { navController.navigate(R.id.action_loginFragment_to_registerFragment) }
            loginbutton.setOnClickListener {
                val password = password.text.toString()
                val email = email.text.toString()
                viewModel.signIn(email, password)
            }
            resetpassword.setOnClickListener { viewModel.passwordReset(binding.email.text.toString()) }
            binding.email.setText(viewModel.loadSavedPref("Email"))
            binding.password.setText(viewModel.loadSavedPref("Password"))
        }

        viewModel.fragmentDestination.observe(viewLifecycleOwner, {
            when (it) {
                "loginToCuisine" -> navController.navigate(R.id.action_loginFragment_to_cuisineFragment)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




