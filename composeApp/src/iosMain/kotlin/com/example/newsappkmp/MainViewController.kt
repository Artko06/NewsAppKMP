package com.example.newsappkmp

import androidx.compose.ui.window.ComposeUIViewController
import com.example.newsappkmp.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) { App() }