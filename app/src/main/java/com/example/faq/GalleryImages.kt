package com.example.faq

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.provider.MediaStore
import android.util.Log

object GalleryUtils {
    fun getGalleryImages(context: Context): List<Bitmap> {
        val imageList = mutableListOf<Bitmap>()
        val uri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        val projection = arrayOf(MediaStore.Images.Media.DATA)

        context.contentResolver.query(uri, projection, null, null, null)?.use { cursor ->
            val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)

            while (cursor.moveToNext()) {
                val imagePath = cursor.getString(columnIndex)
                val bitmap = BitmapFactory.decodeFile(imagePath)
                if (bitmap != null) imageList.add(bitmap)
            }
        }

        Log.d("GalleryUtils", "Found ${imageList.size} images in gallery")
        return imageList
    }

    }

