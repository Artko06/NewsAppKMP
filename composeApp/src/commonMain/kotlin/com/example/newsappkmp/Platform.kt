package com.example.newsappkmp

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform