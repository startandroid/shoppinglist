package ru.startandroid.feature.catalog.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.startandroid.core.common.data.mapping.Mapping
import ru.startandroid.core.database.catalog.folder.FolderDb
import ru.startandroid.feature.catalog.data.mapping.FolderMapDbToDm
import ru.startandroid.feature.catalog.data.mapping.FolderMapDmToDb
import ru.startandroid.feature.catalog.domain.model.FolderDm

@Module
@InstallIn(SingletonComponent::class)
abstract class MappingModule {

    @Binds
    abstract fun bindFolderMapDbToDm(folderMapDbToDm: FolderMapDbToDm): Mapping<FolderDb, FolderDm>

    @Binds
    abstract fun bindFolderMapDmToDb(folderMapDmToDb: FolderMapDmToDb): Mapping<FolderDm, FolderDb>
}