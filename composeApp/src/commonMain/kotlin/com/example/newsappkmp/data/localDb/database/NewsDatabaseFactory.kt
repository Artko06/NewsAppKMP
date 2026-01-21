package com.example.newsappkmp.data.localDb.database

import androidx.room.RoomDatabase

expect class NewsDatabaseFactory {
    fun create(): RoomDatabase.Builder<NewsRoomDatabase>
}