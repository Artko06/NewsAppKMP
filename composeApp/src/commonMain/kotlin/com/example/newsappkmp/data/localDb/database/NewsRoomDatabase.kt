package com.example.newsappkmp.data.localDb.database

import androidx.room.ConstructedBy
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.newsappkmp.data.entity.NewsItemEntity
import com.example.newsappkmp.data.localDb.dao.NewsFavoriteDao

@Database(
    entities = [NewsItemEntity::class],
    version = 1
)
@ConstructedBy(NewsDatabaseConstructor::class)
abstract class NewsRoomDatabase : RoomDatabase() {
    abstract val newsDao: NewsFavoriteDao

    companion object {
        const val NEWS_DB_NAME = "news.db"
    }
}