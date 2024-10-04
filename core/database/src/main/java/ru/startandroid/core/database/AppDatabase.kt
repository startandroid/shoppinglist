package ru.startandroid.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.startandroid.core.database.catalog.folder.FolderDao
import ru.startandroid.core.database.catalog.folder.FolderDb

@Database(entities = [FolderDb::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun folderDao(): FolderDao
}