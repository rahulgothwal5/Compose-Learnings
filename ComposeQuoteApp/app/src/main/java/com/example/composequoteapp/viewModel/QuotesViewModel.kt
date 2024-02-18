package com.example.composequoteapp.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composequoteapp.modal.Quote
import com.example.composequoteapp.repo.QuoteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import com.example.composequoteapp.Result
import javax.inject.Inject

@HiltViewModel
class QuotesViewModel @Inject constructor(
    private val quoteRepo: QuoteRepo
) : ViewModel() {

    private val _quotes = MutableStateFlow(QuotesListUiState.Success(emptyList()))

    val quotes = _quotes

    init {
        viewModelScope.launch {
            when (val result = quoteRepo.fetchQuotes()) {
                is Result.Success ->{
                    _quotes.value = QuotesListUiState.Success(result.data)
                }
                is Result.Error -> {
                    QuotesListUiState.Error(result.exception)
                }
            }
        }
    }


    sealed class QuotesListUiState {
        data class Success(val quotes: List<Quote>) : QuotesListUiState()
        data class Error(val exception: Exception) : QuotesListUiState()
    }

}