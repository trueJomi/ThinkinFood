package com.thinkingfood.androidvovil.ui.recomendaciones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thinkingfood.androidvovil.databinding.FragmentRecomendBinding

class RecomendFragment : Fragment() {

    private var _binding: FragmentRecomendBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecomendBinding.inflate(inflater,container,false)
        val root: View = binding.root
        return root


    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}