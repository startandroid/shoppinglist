package ru.startandroid.feature.catalog.data.mapping

import ru.startandroid.core.common.data.mapping.Mapping
import ru.startandroid.core.database.catalog.folder.FolderDb
import ru.startandroid.feature.catalog.domain.model.FolderDm
import javax.inject.Inject

class FolderMapDbToDm @Inject constructor(): Mapping<FolderDb, FolderDm> {
    override fun map(input: FolderDb): FolderDm {
        return FolderDm(
            id = input.id,
            name = input.name
        )
    }
}