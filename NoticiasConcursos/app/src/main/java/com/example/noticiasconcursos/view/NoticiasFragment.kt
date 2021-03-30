package com.example.noticiasconcursos.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager

import com.example.noticiasconcursos.R
import com.example.noticiasconcursos.api.RetrofitClient
import com.example.noticiasconcursos.databinding.FragmentNoticiasBinding
import com.example.noticiasconcursos.recyclerview.Adapter
import com.example.noticiasconcursos.recyclerview.CardData
import java.util.ArrayList

open class NoticiasFragment : Fragment(R.layout.fragment_noticias,) {

    private var mAdapter : Adapter? = null
    private var _binding : FragmentNoticiasBinding? =null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentNoticiasBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonGetJson.setOnClickListener { RetrofitClient.createClient() }

    }
    fun initRecyclerView() {
        val cardData = ArrayList<CardData>()
        val titulo1 :String = RetrofitClient.meusTitulos[0]
        cardData.add(CardData(titulo1,"descricao","3"))
        binding.recyclerview.apply {
            layoutManager = LinearLayoutManager(context.applicationContext)
            mAdapter = Adapter(cardData)
            adapter = mAdapter
        }


    }
}


