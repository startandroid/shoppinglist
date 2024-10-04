package ru.startandroid.core.database.catalog.folder

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FolderDao {

    @Query("SELECT * FROM folders")
    fun getAll(): Flow<List<FolderDb>>

    @Insert
    suspend fun insert(folder: FolderDb)

    @Query("DELETE FROM folders WHERE id = :id")
    suspend fun delete(id: Long)

    @Query("UPDATE folders SET name = :name WHERE id = :id")
    suspend fun rename(id: Long, name: String)

}