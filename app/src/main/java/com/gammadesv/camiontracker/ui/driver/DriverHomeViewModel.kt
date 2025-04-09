package com.gammadesv.camiontracker.ui.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gammadesv.camiontracker.data.model.Branch
import com.gammadesv.camiontracker.data.model.Movement
import com.gammadesv.camiontracker.data.repository.MovementRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverHomeViewModel @Inject constructor(
    private val movementRepository: MovementRepository
) : ViewModel() {
    private val _selectedBranch = MutableStateFlow<Branch?>(null)
    val selectedBranch: StateFlow<Branch?> = _selectedBranch

    private val _currentMovement = MutableStateFlow<Movement?>(null)
    val currentMovement: StateFlow<Movement?> = _currentMovement

    fun selectBranch(branch: Branch) {
        _selectedBranch.value = branch
        loadCurrentMovement(branch.id)
    }

    private fun loadCurrentMovement(branchId: String) {
        viewModelScope.launch {
            // Implementar lógica para cargar movimiento actual
        }
    }

    fun registerArrival() {
        viewModelScope.launch {
            // Implementar lógica para registrar llegada
        }
    }

    fun registerDeparture() {
        viewModelScope.launch {
            // Implementar lógica para registrar salida
        }
    }
}