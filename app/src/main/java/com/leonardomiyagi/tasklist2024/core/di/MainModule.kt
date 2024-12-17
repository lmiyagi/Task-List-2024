package com.leonardomiyagi.tasklist2024.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Named

@Module
@InstallIn(ActivityComponent::class)
class MainModule {

    @Provides
    @Named("TEST_STRING")
    @ActivityScoped
    fun provideTestString() = "This is a test String provided by Hilt."
}