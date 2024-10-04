package ru.startandroid.feature.catalog.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.startandroid.feature.catalog.data.repository.FolderRepositoryImpl
import ru.startandroid.feature.catalog.domain.repository.FolderRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindFolderRepository(folderRepositoryImpl: FolderRepositoryImpl): FolderRepository

}