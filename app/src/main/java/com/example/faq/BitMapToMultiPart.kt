package com.example.faq

//import android.graphics.Bitmap
//import okhttp3.MediaType.Companion.toMediaTypeOrNull
//import okhttp3.MultipartBody
//import okhttp3.RequestBody.Companion.toRequestBody
//import java.io.ByteArrayOutputStream
//
//fun bitmapToMultipart(bitmap: Bitmap): MultipartBody.Part {
//    val stream = ByteArrayOutputStream()
//    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
//    val byteArray = stream.toByteArray()
//    val requestBody = byteArray.toRequestBody("image/*".toMediaTypeOrNull())
//    return MultipartBody.Part.createFormData("image", "photo.jpg", requestBody)
//}
