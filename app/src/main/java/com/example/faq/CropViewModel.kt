package com.example.faq

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class CropViewModel:ViewModel() {
    private val _uiState= MutableStateFlow(CropUiState())
    val uiState: StateFlow<CropUiState> = _uiState.asStateFlow()

    fun updateClickText(updatedText:String){
        _uiState.update {
            it.copy(clickStatus = updatedText)
        }
    }
    fun updateSelectedCategory(updatedCategory:String){
        _uiState.update {
            it.copy(selectedCategory = updatedCategory)
        }

    }
    private val _selectedCamera = MutableStateFlow("")
    val selectedDate: StateFlow<String> = _selectedCamera

    fun updateSelectedDate(date: String) {
        _selectedCamera.value = date
    }
}