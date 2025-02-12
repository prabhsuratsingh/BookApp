package com.example.bookapp.data

import retrofit2.http.GET
import retrofit2.http.Query

interface BookApi {
    @GET("volumes")//?q=thriller
    suspend fun getBooks(@Query("q") query : String) : BookResponse
}
