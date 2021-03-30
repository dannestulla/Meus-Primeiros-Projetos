package com.example.noticiasconcursos.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

import com.example.noticiasconcursos.viewmodel.MainActivityViewModel
import com.example.noticiasconcursos.R
import com.example.noticiasconcursos.databinding.FragmentAdicionarBinding
import com.example.noticiasconcursos.room.Entity


class AdicionarFragment : Fragment(R.layout.fragment_adicionar) {
    private lateinit var mMainActivityViewModel : MainActivityViewModel
    private lateinit var entity : Entity
    private var _binding : FragmentAdicionarBinding? =null
    private val binding get() = _binding!! //bypassando o not null check do Kotlin

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentAdicionarBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mMainActivityViewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        binding.buttoninsert.setOnClickListener {insertDataToDatabase()}
        binding.button2.setOnClickListener {deleteAllData()}
        binding.button3.setOnClickListener { delete() }


    }
    private fun getEntity() {
        entity = Entity(binding.editText1.text.toString().toInt(), binding.editText2.text.toString(), binding.editText3.text.toString(), binding.editText4.text.toString().toInt())
    }
    private fun delete() {
        Log.e("Button","Pressed")
        getEntity()
        mMainActivityViewModel.delete(entity)
    }
    private fun deleteAllData() {
        Log.e("Button","Pressed")
        getEntity()
        mMainActivityViewModel.deleteAll()
    }

    private fun insertDataToDatabase() {
        Log.e("Button","Pressed")
        getEntity()
        mMainActivityViewModel.addUser(entity)
    }

}