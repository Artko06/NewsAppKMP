package com.example.newsappkmp.data.localDb.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

actual class NewsDatabaseFactory(
    private val context: Context
) {
    actual fun create(): RoomDatabase.Builder<NewsRoomDatabase> {
        val appContext = context.applicationContext
        val dbFile = appContext.getDatabasePath(NewsRoomDatabase.NEWS_DB_NAME)

        return Room.databaseBuilder(
            context = appContext,
            name = dbFile.absolutePath
        )
    }
}