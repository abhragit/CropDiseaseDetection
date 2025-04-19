package com.example.faq

import android.graphics.Bitmap
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

import kotlinx.coroutines.flow.asStateFlow


class MainViewModel:ViewModel() {



    private val _bitmaps = MutableStateFlow<List<Bitmap>>(emptyList())
    val bitmaps = _bitmaps.asStateFlow()

    fun onTakePhoto(bitmap: Bitmap){
        _bitmaps.value += bitmap
    }
    fun addGalleryImages(newImages: List<Bitmap>) {
        val currentImages = _bitmaps.value.toMutableList()
        // Avoid duplicates (can be improved with better comparison if needed)
        newImages.forEach { newBitmap ->
            if (currentImages.none { it.sameAs(newBitmap) }) {
                currentImages.add(newBitmap)
            }
        }
        _bitmaps.value = currentImages
    }

}