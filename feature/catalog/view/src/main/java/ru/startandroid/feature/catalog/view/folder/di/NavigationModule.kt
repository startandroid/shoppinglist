package ru.startandroid.feature.catalog.view.folder.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntoSet
import ru.startandroid.core.navigation.NavScreen
import ru.startandroid.feature.catalog.view.folder.navigation.FoldersNavScreen

@Module
@InstallIn(SingletonComponent::class)
abstract class NavigationModule {

    @Binds
    @IntoSet
    abstract fun bindFoldersNavScreen(impl: FoldersNavScreen): NavScreen
}