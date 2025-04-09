package com.gammadesv.camiontracker.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gammadesv.camiontracker.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val viewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnLogin.setOnClickListener {
            val employeeCode = binding.etEmployeeCode.text.toString().trim()
            val password = binding.etPassword.text.toString().trim()

            if (validateInputs(employeeCode, password)) {
                viewModel.login(employeeCode, password)
            }
        }
    }

    private fun validateInputs(employeeCode: String, password: String): Boolean {
        var isValid = true

        if (employeeCode.isEmpty()) {
            binding.etEmployeeCode.error = "Ingrese su código de empleado"
            isValid = false
        }

        if (password.isEmpty()) {
            binding.etPassword.error = "Ingrese su contraseña"
            isValid = false
        }

        return isValid
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}