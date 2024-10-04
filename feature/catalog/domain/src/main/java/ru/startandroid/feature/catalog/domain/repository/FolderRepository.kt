package ru.startandroid.feature.catalog.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.startandroid.feature.catalog.domain.model.FolderDm

interface FolderRepository {
    fun getFolders(): Flow<List<FolderDm>>
    suspend fun addFolder(folder: FolderDm)
    suspend fun deleteFolder(id: Long)
    suspend fun renameFolder(id: Long, name: String)
}