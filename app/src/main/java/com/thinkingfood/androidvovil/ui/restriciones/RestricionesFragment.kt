package com.thinkingfood.androidvovil.ui.restriciones

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.thinkingfood.androidvovil.R
import com.thinkingfood.androidvovil.databinding.FragmentRestriccionesBinding


class RestricionesFragment : Fragment() {

    private var _binding: FragmentRestriccionesBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRestriccionesBinding.inflate(inflater,container,false)
        return inflater.inflate(R.layout.fragment_restricciones, container, false)
    }
}