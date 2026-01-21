package com.example.newsappkmp.data.localDb.database

import androidx.room.RoomDatabaseConstructor

// The Room compiler generates the `actual` implementations.
@Suppress("NO_ACTUAL_FOR_EXPECT")
expect object NewsDatabaseConstructor : RoomDatabaseConstructor<NewsRoomDatabase> {
    override fun initialize(): NewsRoomDatabase
}
