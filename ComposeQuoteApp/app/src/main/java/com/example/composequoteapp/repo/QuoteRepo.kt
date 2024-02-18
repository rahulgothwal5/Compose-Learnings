package com.example.composequoteapp.repo

import com.example.composequoteapp.modal.Quote
import com.example.composequoteapp.Result


interface QuoteRepo {
    public suspend fun fetchQuotes(): Result<List<Quote>>

}