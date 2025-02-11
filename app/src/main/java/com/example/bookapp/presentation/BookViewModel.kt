package com.example.bookapp.presentation

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookapp.data.VolumeInfo
import com.example.bookapp.data.apiService
import kotlinx.coroutines.launch

class BookViewModel : ViewModel() {
    private val _books = mutableStateOf<List<VolumeInfo>>(emptyList())
    val books = _books

    fun searchBooks(query: String) {
        viewModelScope.launch {
            try {
                val response = apiService.getBooks(query)
                _books.value = response.items.map { it.volumeInfo }
            } catch (e: Exception) {
                Log.e("BooksViewModel", "Error fetching books", e)
            }
        }
    }
}