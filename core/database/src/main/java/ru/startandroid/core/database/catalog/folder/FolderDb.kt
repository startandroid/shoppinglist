package ru.startandroid.core.database.catalog.folder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "folders")
data class FolderDb(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name: String,
)