package ru.startandroid.feature.catalog.data.repository

import kotlinx.coroutines.flow.Flow
import ru.startandroid.core.common.data.extensions.mapListWith
import ru.startandroid.core.common.data.mapping.Mapping
import ru.startandroid.core.database.catalog.folder.FolderDao
import ru.startandroid.core.database.catalog.folder.FolderDb
import ru.startandroid.feature.catalog.domain.model.FolderDm
import ru.startandroid.feature.catalog.domain.repository.FolderRepository
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
    private val folderDao: FolderDao,
    private val folderMapDbToDm: Mapping<FolderDb, FolderDm>,
    private val folderMapDmToDb: Mapping<FolderDm, FolderDb>
): FolderRepository {

    override fun getFolders(): Flow<List<FolderDm>> {
        return folderDao.getAll().mapListWith(folderMapDbToDm)
    }

    override suspend fun addFolder(folder: FolderDm) {
        folderDao.insert(folderMapDmToDb.map(folder))
    }

    override suspend fun deleteFolder(id: Long) {
        folderDao.delete(id)
    }

    override suspend fun renameFolder(id: Long, name: String) {
        folderDao.rename(id, name)
    }
}