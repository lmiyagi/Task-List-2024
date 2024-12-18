package com.leonardomiyagi.tasklist2024.core.di

import android.content.Context
import androidx.room.Room
import com.leonardomiyagi.tasklist2024.core.DB_NAME
import com.leonardomiyagi.tasklist2024.data.local.DefaultDatabase
import com.leonardomiyagi.tasklist2024.data.repository.DefaultTaskRepository
import com.leonardomiyagi.tasklist2024.data.repository.TaskRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class TaskModule {

    companion object {
        @Provides
        @Singleton
        fun provideDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
            context,
            DefaultDatabase::class.java,
            DB_NAME
        ).build()

        @Provides
        @Singleton
        fun provideTaskDAO(db: DefaultDatabase) = db.taskDAO()
    }

    @Binds
    @Singleton
    abstract fun provideTaskRepository(defaultTaskRepository: DefaultTaskRepository): TaskRepository
}