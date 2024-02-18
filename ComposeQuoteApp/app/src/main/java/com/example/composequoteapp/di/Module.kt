package com.example.composequoteapp.di

import android.app.Application
import android.content.Context
import androidx.compose.runtime.Composable
import com.example.composequoteapp.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Provides
    fun provideApplicationContext(@ApplicationContext context: Context): Context {
        return context
    }

}