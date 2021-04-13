package com.example.noticiasconcursos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.noticiasconcursos.R
import com.example.noticiasconcursos.databinding.FragmentDescriptionBinding
import com.example.noticiasconcursos.features.NoticiasViewModel.Companion.cardData
import com.example.noticiasconcursos.features.NoticiasViewModel.Companion.myDescription
import com.example.noticiasconcursos.features.NoticiasViewModel.Companion.myImages
import com.example.noticiasconcursos.features.NoticiasViewModel.Companion.position
import com.example.noticiasconcursos.features.NoticiasViewModel.Companion.result
import com.squareup.picasso.Picasso
import org.jsoup.Jsoup
import org.jsoup.nodes.Document


class DescriptionFragment : Fragment(R.layout.fragment_description) {

    private var _binding : FragmentDescriptionBinding? =null
    private val binding get() = _binding!!
    lateinit var image : String
    lateinit var titulo : String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _binding = FragmentDescriptionBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //aplicando imagem
        if (position == 0 ) {image = cardData[2].toString()}
        else {image = cardData[position!!*3+2].toString()}
        Picasso.get().load(image).into(binding.imageView)

        //aplicando titulo
        if (position == 0) {titulo = cardData[0].toString()}
        else {titulo = cardData[position!!*3].toString()}
        binding.textViewtitulo.text = titulo

        val html = myDescription[position!!]
        val doc: Document = Jsoup.parse(html)
        val text: String = doc.body().text() // "An example link"
        binding.textViewDescription.text = text


    }



}