package ru.startandroid.feature.catalog.view.folder.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import ru.startandroid.core.ui.components.dialog.CstConfirmationDialogStateHolder
import ru.startandroid.core.ui.components.dialog.CstTextInputDialogStateHolder
import ru.startandroid.feature.catalog.view.folder.model.Folder
import javax.inject.Inject





@HiltViewModel
class FoldersViewModel @Inject constructor(
    val createFolderDialogStateHolder: CstTextInputDialogStateHolder,
    val editFolderDialogStateHolder: CstTextInputDialogStateHolder,
    val deleteFolderDialogStateHolder: CstConfirmationDialogStateHolder
) : ViewModel() {

    private var idCounter = 5
    val folders = MutableStateFlow<List<Folder>>(
        List(idCounter) { Folder(id = it, name = "Folder $it") }
    )

    private val _selectedFoldersIds = MutableStateFlow<Set<Int>>(emptySet())
    val selectedFoldersIds = _selectedFoldersIds.asStateFlow()

    val singleSelected = selectedFoldersIds.map { it.size == 1 }
    val multipleSelected = selectedFoldersIds.map { it.isNotEmpty() }

    fun onFolderClick(id: Int) {

    }

    fun onFolderLongClick(id: Int) {
        changeFolderSelection(id)
    }

    fun onFolderIconClick(id: Int) {
        changeFolderSelection(id)
    }


    fun onCreateFolderClick() {
        createFolderDialogStateHolder.show("")
    }


    fun onCreateFolderDialogConfirmed(name: String) {
        val newFolder = Folder(id = idCounter++, name = name)
        folders.value += newFolder
    }


    fun onEditFolderClick() {
        val selectedFolder = getSelectedFolders().firstOrNull() ?: return
        editFolderDialogStateHolder.show(selectedFolder.name)
    }

    fun onEditFolderDialogConfirmed(name: String) {
        val selectedFolderId = selectedFoldersIds.value.firstOrNull() ?: return
        folders.update {
            it.map { folder ->
                if (folder.id == selectedFolderId) {
                    folder.copy(name = name)
                } else {
                    folder
                }
            }
        }
        clearSelection()
    }

    fun onDeleteFolderClick() {
        deleteFolderDialogStateHolder.show()
    }

    fun onDeleteFolderDialogConfirmed() {
        folders.value = folders.value.filter { it.id !in selectedFoldersIds.value }
        clearSelection()
    }

    fun onCreateProductClick() {

    }


    private fun clearSelection() {
        _selectedFoldersIds.value = emptySet()
    }

    private fun getSelectedFolders(): List<Folder> {
        return folders.value.filter { it.id in selectedFoldersIds.value }
    }

    private fun changeFolderSelection(id: Int) {
        if (_selectedFoldersIds.value.contains(id)) {
            _selectedFoldersIds.value -= id
        } else {
            _selectedFoldersIds.value += id
        }
    }

}