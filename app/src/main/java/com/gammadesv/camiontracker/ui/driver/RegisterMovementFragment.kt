package com.gammadesv.camiontracker.ui.driver

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gammadesv.camiontracker.databinding.FragmentRegisterMovementBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RegisterMovementFragment : Fragment() {
    private var _binding: FragmentRegisterMovementBinding? = null
    private val binding get() = _binding!!
    private val viewModel: DriverHomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRegisterMovementBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Configurar listeners y observadores
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}