package com.example.newsappkmp.presentation.news_detail.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.newsappkmp.presentation.news_detail.action.NewsDetailAction
import com.example.newsappkmp.presentation.news_detail.state.NewsDetailState
import com.example.newsappkmp.presentation.news_detail.viewModel.NewsDetailViewModel

@Composable
fun NewsDetailScreen(
    newsDetailViewModel: NewsDetailViewModel,
    onBackClick: () -> Unit,
    urlNews: String,
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = urlNews,
            modifier = Modifier.padding(12.dp)
        )
    }
}