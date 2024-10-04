package ru.startandroid.feature.catalog.data.mapping

import ru.startandroid.core.common.data.mapping.Mapping
import ru.startandroid.core.database.catalog.folder.FolderDb
import ru.startandroid.feature.catalog.domain.model.FolderDm
import javax.inject.Inject

class FolderMapDmToDb @Inject constructor() : Mapping<FolderDm, FolderDb> {
    override fun map(input: FolderDm): FolderDb {
        return FolderDb(
            id = input.id,
            name = input.name
        )
    }
}