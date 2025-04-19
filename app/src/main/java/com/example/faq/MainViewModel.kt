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
    fun addGalleryImages(images: List<Bitmap>) {
        _bitmaps.value += images
    }
}