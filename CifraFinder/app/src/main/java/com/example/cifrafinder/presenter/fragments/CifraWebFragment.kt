package com.example.cifrafinder.presenter.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.cifrafinder.CifraConstants
import com.example.cifrafinder.databinding.FragmentWebViewBinding
import com.example.cifrafinder.presenter.viewModels.CifraWebViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class CifraWebFragment : Fragment() {

    private var _viewBinding: FragmentWebViewBinding? = null
    private val viewBinding get() = _viewBinding!!

    private val viewModel: CifraWebViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _viewBinding = FragmentWebViewBinding.inflate(inflater, container, false)
        return viewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyBinding()
        setSearchObserver()
        getSongUrl()
    }

    private fun getSongUrl() {
        val searchText = this.arguments?.getString(CifraConstants.searchText)
        searchText?.let { viewModel.getSongUrl(it) }
    }

    private fun setSearchObserver() =
        viewModel.searchUrl.observe(viewLifecycleOwner, { searchUrl ->
            if (searchUrl.isNotEmpty()) {
                viewBinding.webView.loadUrl(searchUrl)
            }
        })

    private fun applyBinding() = with(viewBinding) {
        refreshButton.setOnClickListener { webView.reload() }
        webView.webViewClient = WebViewClient()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _viewBinding = null
    }
}