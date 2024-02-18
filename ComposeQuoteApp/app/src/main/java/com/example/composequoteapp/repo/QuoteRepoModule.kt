package com.example.composequoteapp.repo

import com.google.android.gms.analytics.AnalyticsService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class QuoteRepoModule {

  @Binds
  abstract fun bindQuoteRepo(
    quoteRepo: QuoteRepoImpl
  ): QuoteRepo
}