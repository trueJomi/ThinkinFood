package com.thinkingfood.androidvovil.ui.home.subViews

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.thinkingfood.androidvovil.viewsModel.ListRecetasViewModel
import com.thinkingfood.androidvovil.databinding.FragmentRecetasContentBinding
import com.thinkingfood.androidvovil.factory.RecetaViewModelFactory
import com.thinkingfood.androidvovil.model.Receta
import com.thinkingfood.androidvovil.viewsModel.RecetaViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class RecetasContentFragment : Fragment() {
    private val args: RecetasContentFragmentArgs by navArgs()
    private val recetaViewModel:RecetaViewModel by viewModels{
        RecetaViewModelFactory(args.id)
    }
    private val regexYoutUbe = "^(http(s)?:\\/\\/)?((w){3}.)?youtu(be|.be)?(\\.com)?\\/.+";
    private var _binding: FragmentRecetasContentBinding? = null
    private lateinit var webView:WebView
    private val binding get() = checkNotNull(_binding)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this._binding = FragmentRecetasContentBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        webView = binding.video
        webView.settings.javaScriptEnabled= true
        webView.webViewClient= WebViewClient()
        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.lifecycle.repeatOnLifecycle(Lifecycle.State.CREATED){
                recetaViewModel.receta.collect{ receta->
                    receta.let {
                        binding.apply {
                            textView.text=it?.nombre
                            val value =it?.linkVideo?.replace("/watch?v=","/embed/")
                            val frameVideo="<iframe width=\"100%\" height=\"100%\" src=\"${value}\" frameborder=\"0\"></iframe>"
                            webView.loadData(frameVideo,"text/html", "utf-8")
                        }

                    }
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}