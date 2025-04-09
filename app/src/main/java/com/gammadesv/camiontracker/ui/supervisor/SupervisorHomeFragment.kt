package com.gammadesv.camiontracker.ui.supervisor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gammadesv.camiontracker.databinding.FragmentSupervisorHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SupervisorHomeFragment : Fragment() {
    private var _binding: FragmentSupervisorHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel: SupervisorHomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSupervisorHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Configurar UI y listeners
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}