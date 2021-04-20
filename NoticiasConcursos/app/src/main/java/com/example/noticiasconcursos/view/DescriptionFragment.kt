package com.example.noticiasconcursos.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.noticiasconcursos.R
import com.example.noticiasconcursos.databinding.FragmentWebviewBinding
import com.example.noticiasconcursos.features.NoticiasViewModel
import com.example.noticiasconcursos.features.NoticiasViewModel.Companion.myLinks
import com.example.noticiasconcursos.features.NoticiasViewModel.Companion.position


class DescriptionFragment : Fragment(R.layout.fragment_webview) {
    private var _binding: FragmentWebviewBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: NoticiasViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentWebviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as NoticiasActivity).viewModel

        if (viewModel.isNetworkAvaliable) {
            binding.webView.apply {
                webViewClient = WebViewClient()
                loadUrl(myLinks[position!!])
            }
        } else {
            Toast.makeText(context,"Conexão com a internet necessária para carregar a notícia", Toast.LENGTH_LONG).show()
            /* Picasso.get().load(myImages[position!!]).into(binding.imageView)
            binding.textViewtitulo.text = myTitles[position!!]
            val html = myDescription[position!!]
            val doc: Document = Jsoup.parse(html)
            val parsedMyDescription: String = doc.body().text() // "An example link"
            binding.textViewDescription.text = parsedMyDescription */
        }
    }




}