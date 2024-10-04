package ru.startandroid.feature.catalog.view.folder.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import ru.startandroid.core.ui.components.dialog.CstConfirmationDialogStateHolder
import ru.startandroid.core.ui.components.dialog.CstTextInputDialogStateHolder
import ru.startandroid.core.ui.extensions.stateInVm
import ru.startandroid.feature.catalog.domain.model.FolderDm
import ru.startandroid.feature.catalog.domain.repository.FolderRepository
import javax.inject.Inject


@HiltViewModel
class FoldersViewModel @Inject constructor(
    private val folderRepository: FolderRepository,
    val createFolderDialogStateHolder: CstTextInputDialogStateHolder,
    val editFolderDialogStateHolder: CstTextInputDialogStateHolder,
    val deleteFolderDialogStateHolder: CstConfirmationDialogStateHolder
) : ViewModel() {

    val folders = folderRepository.getFolders().stateInVm()

    private val _selectedFoldersIds = MutableStateFlow<Set<Long>>(emptySet())
    val selectedFoldersIds = _selectedFoldersIds.asStateFlow()

    val singleSelected = selectedFoldersIds.map { it.size == 1 }
    val multipleSelected = selectedFoldersIds.map { it.isNotEmpty() }

    fun onFolderClick(id: Long) {

    }

    fun onFolderLongClick(id: Long) {
        changeFolderSelection(id)
    }

    fun onFolderIconClick(id: Long) {
        changeFolderSelection(id)
    }


    fun onCreateFolderClick() {
        createFolderDialogStateHolder.show("")
    }


    fun onCreateFolderDialogConfirmed(name: String) {
        viewModelScope.launch {
            folderRepository.addFolder(FolderDm(name = name))
        }
    }


    fun onEditFolderClick() {
        val selectedFolder = getSelectedFolders().firstOrNull() ?: return
        editFolderDialogStateHolder.show(selectedFolder.name)
    }

    fun onEditFolderDialogConfirmed(name: String) {
        val selectedFolderId = selectedFoldersIds.value.firstOrNull() ?: return
        viewModelScope.launch {
            folderRepository.renameFolder(selectedFolderId, name)
        }
        clearSelection()
    }

    fun onDeleteFolderClick() {
        deleteFolderDialogStateHolder.show()
    }

    fun onDeleteFolderDialogConfirmed() {
        viewModelScope.launch {
            selectedFoldersIds.value.forEach { id ->
                folderRepository.deleteFolder(id)
            }
        }
        clearSelection()
    }

    fun onCreateProductClick() {

    }


    private fun clearSelection() {
        _selectedFoldersIds.value = emptySet()
    }

    private fun getSelectedFolders(): List<FolderDm> {
        return folders.value.filter { it.id in selectedFoldersIds.value }
    }

    private fun changeFolderSelection(id: Long) {
        if (_selectedFoldersIds.value.contains(id)) {
            _selectedFoldersIds.value -= id
        } else {
            _selectedFoldersIds.value += id
        }
    }

}