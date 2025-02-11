package com.example.bookapp.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookapp.data.VolumeInfo

@Composable
fun BookScreen(
    modifier: Modifier = Modifier,
    viewModel: BookViewModel = viewModel()
) {
    var searchQuery by remember { mutableStateOf(" ") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        OutlinedTextField(
            value = searchQuery,
            onValueChange = {searchQuery = it},
            label = { Text("enter genre") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        Button(
            onClick ={viewModel.searchBooks(searchQuery)}
        ) {
            Text("Search")
        }

        Spacer(modifier = Modifier.height(8.dp))


        val books by viewModel.books
        LazyColumn {
            items(books) { book ->
                BookItemView(book)
            }
        }
    }
}

@Composable
fun BookItemView(book: VolumeInfo) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = book.title, fontWeight = FontWeight.Bold, fontSize = 18.sp)
        book.authors?.let { Text(text = "By: ${it.joinToString(", ")}", fontStyle = FontStyle.Italic) }
        book.description?.let { Text(text = it, maxLines = 3, overflow = TextOverflow.Ellipsis) }
    }
}