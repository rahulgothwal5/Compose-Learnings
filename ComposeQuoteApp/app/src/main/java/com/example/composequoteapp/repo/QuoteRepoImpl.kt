package com.example.composequoteapp.repo

import android.content.Context
import com.example.composequoteapp.modal.Quote
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.delay
import java.io.IOException
import com.example.composequoteapp.Result
import javax.inject.Inject


@ViewModelScoped
class QuoteRepoImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : QuoteRepo {
    override suspend fun fetchQuotes(): Result<List<Quote>> {
        return try {
            val content = context.assets.open("response.json").bufferedReader()
                .use {
                    it.readText()
                }
            val gson = Gson()
            val quoteListType = object : TypeToken<List<Quote>>() {}.type
            val quotes: List<Quote> = gson.fromJson(content, quoteListType)
            delay(2000)
            return Result.Success(quotes)

        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return Result.Error(ioException)
        }
    }
}





