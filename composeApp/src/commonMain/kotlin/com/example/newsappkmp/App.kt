package com.example.newsappkmp

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import com.example.newsappkmp.presentation.navigation.NavigationScreen
import org.jetbrains.compose.ui.tooling.preview.Preview


@Composable
@Preview
fun App() {
    MaterialTheme {
        NavigationScreen()
    }
}