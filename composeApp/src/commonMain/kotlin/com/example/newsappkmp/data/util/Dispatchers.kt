package com.example.newsappkmp.data.util

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

val uiDispatcher: CoroutineDispatcher = Dispatchers.Main

val ioDispatcher: CoroutineDispatcher = Dispatchers.IO

val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default