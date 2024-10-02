package ru.startandroid.feature.catalog.view.folder.navigation

import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import ru.startandroid.core.navigation.NavContext
import ru.startandroid.core.navigation.NavScreen
import ru.startandroid.feature.catalog.view.folder.ui.FoldersScreen
import javax.inject.Inject

class FoldersNavScreen @Inject constructor(): NavScreen {

    @Serializable
    data object FoldersRoute

    override fun addToGraph(navContext: NavContext) {
        navContext.navGraphBuilder.composable<FoldersRoute> {
            FoldersScreen()
        }
    }

}