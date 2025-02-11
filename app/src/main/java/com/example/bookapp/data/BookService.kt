package com.example.bookapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val apiService = Retrofit.Builder()
    .baseUrl("https://www.googleapis.com/books/v1/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()
    .create(BookApi::class.java)