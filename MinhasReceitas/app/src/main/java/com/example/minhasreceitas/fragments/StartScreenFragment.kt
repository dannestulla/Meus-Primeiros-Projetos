package com.example.minhasreceitas.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.navigation.Navigation
import com.example.minhasreceitas.R
import com.example.minhasreceitas.ReceitasViewModel
import com.example.minhasreceitas.databinding.FragmentStartScreenBinding


class StartScreenFragment : Fragment() {
    lateinit var textLogIn: TextView

    private var _binding: FragmentStartScreenBinding? = null
    private val binding get() = _binding!!

    val viewModel by activityViewModels<ReceitasViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartScreenBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = Navigation.findNavController(view)

        binding.apply {
            button.setOnClickListener { navController.navigate(R.id.action_startScreenFragment_to_registerFragment) }
            textView4.setOnClickListener { navController.navigate(R.id.action_startScreenFragment_to_loginFragment) }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




