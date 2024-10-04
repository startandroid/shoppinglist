package ru.startandroid.core.database.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.startandroid.core.database.AppDatabase

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    fun provideDatabase(app: Application): AppDatabase {
        return Room.databaseBuilder(
            app.applicationContext,
            AppDatabase::class.java,
            "shopping_list_db"
        ).build()
    }

    @Provides
    fun provideFolderDao(db: AppDatabase) = db.folderDao()

}