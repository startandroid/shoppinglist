package ru.startandroid.feature.catalog.view.folder.ui

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.startandroid.core.ui.components.dialog.CstConfirmationDialog
import ru.startandroid.core.ui.components.dialog.CstTextInputDialog
import ru.startandroid.core.ui.components.fab.CstFab
import ru.startandroid.core.ui.components.fab.CstMultipleFab
import ru.startandroid.core.ui.components.fab.CstSmallFab
import ru.startandroid.core.ui.components.screen.CstScreen
import ru.startandroid.core.ui.components.topbar.CstTopBar
import ru.startandroid.core.ui.components.topbar.CstTopBarIcon
import ru.startandroid.feature.catalog.view.folder.model.Folder

@Composable
fun FoldersScreen(
    viewModel: FoldersViewModel = hiltViewModel()
) {
    var fabExpanded by rememberSaveable() { mutableStateOf(false) }
    CstScreen(
        topBar = {
            TopBar(
                singleSelected = viewModel.singleSelected.collectAsState(false).value,
                multipleSelected = viewModel.multipleSelected.collectAsState(false).value,
                onEditFolderClick = viewModel::onEditFolderClick,
                onDeleteFolderClick = viewModel::onDeleteFolderClick
            )
        },
        fab = {
            Fab(
                expanded = fabExpanded,
                onExpandedChange = { fabExpanded = it },
                onCreateProductClick = viewModel::onCreateProductClick,
                onCreateFolderClick = viewModel::onCreateFolderClick
            )
        },
        showFabOverlay = fabExpanded,
        onDismissFabOverlay = { fabExpanded = false }
    ) {
        val folders = viewModel.folders.collectAsState().value
        val selectedFoldersIds = viewModel.selectedFoldersIds.collectAsState().value
        LazyColumn {
            items(items = folders, key = { it.id }) { folder ->
                FolderItem(
                    folder = folder,
                    onClick = viewModel::onFolderClick,
                    onLongClick = viewModel::onFolderLongClick,
                    onIconClick = viewModel::onFolderIconClick,
                    selected = selectedFoldersIds.contains(folder.id)
                )
            }
        }
    }

    CstTextInputDialog(
        holder = viewModel.createFolderDialogStateHolder,
        positiveText = "Create",
        positiveAction = viewModel::onCreateFolderDialogConfirmed,
    )

    CstTextInputDialog(
        holder = viewModel.editFolderDialogStateHolder,
        positiveText = "Save",
        positiveAction = viewModel::onEditFolderDialogConfirmed,
    )

    CstConfirmationDialog(
        holder = viewModel.deleteFolderDialogStateHolder,
        message = "Are you sure you want to delete selected folders?",
        positiveText = "Delete",
        positiveAction = viewModel::onDeleteFolderDialogConfirmed
    )
}

@Composable
private fun TopBar(
    singleSelected: Boolean,
    multipleSelected: Boolean,
    onEditFolderClick: () -> Unit,
    onDeleteFolderClick: () -> Unit
) {
    CstTopBar(
        text = "Folders",
        iconsContent = {
            if (singleSelected) {
                CstTopBarIcon(
                    icon = Icons.Default.Edit,
                    onClick = onEditFolderClick
                )
            }
            if (multipleSelected) {
                CstTopBarIcon(
                    icon = Icons.Default.Delete,
                    onClick = onDeleteFolderClick
                )
            }
        }
    )
}

@Composable
private fun Fab(
    expanded: Boolean,
    onExpandedChange: (Boolean) -> Unit,
    onCreateProductClick: () -> Unit,
    onCreateFolderClick: () -> Unit,
) {
    CstMultipleFab(
        expanded = expanded,
        onExpandedChange = onExpandedChange,
        mainContent = {
            Icon(
                imageVector = Icons.Filled.Add,
                contentDescription = "add"
            )
        },
        actions = listOf(
            { CstSmallFab(label = "Folder", onClick = {
                onExpandedChange(false)
                onCreateFolderClick()
            }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "add"
                )
            }},
            { CstFab(label = "Product", onClick = {
                onExpandedChange(false)
                onCreateProductClick()
            }) {
                Icon(
                    imageVector = Icons.Filled.AccountBox,
                    contentDescription = "add"
                )
            }}
        )
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
private fun FolderItem(
    folder: Folder,
    onClick: (Int) -> Unit,
    onLongClick: (Int) -> Unit,
    onIconClick: (Int) -> Unit,
    selected: Boolean
) {
    Box(modifier = Modifier.padding(vertical = 2.dp)) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth().height(64.dp)
                .background(
                    color = if (selected) Color.LightGray else Color.Transparent,
                    shape = RoundedCornerShape(8.dp)
                )
                .combinedClickable(
                    onClick = { onClick(folder.id) },
                    onLongClick = { onLongClick(folder.id) }
                )
                .padding(8.dp)
        ) {
            Box(
                modifier = Modifier.padding(end = 8.dp).size(40.dp).clickable(
                    onClick = { onIconClick(folder.id) },
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                )
            ) {
                AnimatedContent(
                    targetState = selected,
                ) { selected ->
                    if (selected) {
                        Icon(
                            imageVector = Icons.Default.CheckCircle,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize()
                        )
                    }
                }
            }
            Text(text = folder.name, fontSize = 20.sp)
        }
    }
}
