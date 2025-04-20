package com.example.faq

import android.graphics.Bitmap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow

import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class MainViewModel : ViewModel() {
    private val _cameraBitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    private val _galleryBitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    private val _selectedImage = MutableStateFlow<Bitmap?>(null)

    val bitmaps = combine(_cameraBitmaps, _galleryBitmaps) { cam, gal -> cam + gal }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val selectedImage: StateFlow<Bitmap?> = _selectedImage

    fun onTakePhoto(bitmap: Bitmap) {
        _cameraBitmaps.value = _cameraBitmaps.value + bitmap
    }

    fun addGalleryImages(galleryImages: List<Bitmap>) {
        _galleryBitmaps.value = galleryImages
    }

    fun onImageSelected(bitmap: Bitmap) {
        _selectedImage.value = bitmap
    }

    fun clearSelectedImage() {
        _selectedImage.value = null
    }
}

