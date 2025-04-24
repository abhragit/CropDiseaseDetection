package com.example.faq


import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.Response

interface PredictionApiService {
    @Multipart
    @POST("/predict")
    suspend fun predictDisease(
        @Part image: MultipartBody.Part
    ): retrofit2.Response<PredictionResponse>
}

data class PredictionResponse(
    val disease: String,
    val confidence: Float,
   // val message: String
)

