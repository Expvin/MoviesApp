package com.expv1n.myfilmsapp.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.expv1n.myfilmsapp.databinding.FragmentDetailedInfoBinding

class DetailedInfoFragment : Fragment() {

    private var _binding: FragmentDetailedInfoBinding? = null
    private val binding: FragmentDetailedInfoBinding
        get() = _binding ?: throw RuntimeException("Unknown binding")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailedInfoBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}