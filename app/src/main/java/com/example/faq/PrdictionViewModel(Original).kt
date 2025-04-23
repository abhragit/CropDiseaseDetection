package com.example.faq

import android.graphics.Bitmap
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import com.example.faq.network.ApiClient
//import com.example.faq.network.PredictionResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream

class PredictionViewModel : ViewModel() {

    private val _predictionResult = MutableStateFlow<PredictionResponse?>(null)
    val predictionResult: StateFlow<PredictionResponse?> = _predictionResult

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage: StateFlow<String?> = _errorMessage

    fun predictDiseaseFromImage(bitmap: Bitmap) {
        viewModelScope.launch {
            _isLoading.value = true
            _errorMessage.value = null

            try {
                val file = bitmapToFile(bitmap)
                val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())
                val body = MultipartBody.Part.createFormData("image", file.name, requestFile)

                val response = ApiClient.retrofit.predictDisease(body)

                if (response.isSuccessful && response.body() != null) {
                    _predictionResult.value = response.body()
                } else {
                    _errorMessage.value = "Prediction failed: ${response.code()}"
                }
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.localizedMessage}"
            } finally {
                _isLoading.value = false
            }
        }
    }

    private fun bitmapToFile(bitmap: Bitmap): File {
        val file = File.createTempFile("upload", ".jpg")
        val outputStream = FileOutputStream(file)
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        outputStream.flush()
        outputStream.close()
        return file
    }
}
