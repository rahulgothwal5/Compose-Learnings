package com.example.composequoteapp

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.composequoteapp.destinations.QuoteDetailScreenDestination
import com.example.composequoteapp.modal.Quote
import com.example.composequoteapp.ui.ListItem
import com.example.composequoteapp.ui.TopBar
import com.example.composequoteapp.viewModel.QuotesViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.asStateFlow
import kotlin.random.Random

@RootNavGraph(start = true) // sets this as the start destination of the default nav graph
@Destination
@Composable
fun QuoteListScreen(
    navigator: DestinationsNavigator
) {
    val vm = hiltViewModel<QuotesViewModel>()
    val viewModel: QuotesViewModel = viewModel()
    var quoteList = vm.quotes.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {

            TopBar()

            LazyColumn(Modifier.fillMaxWidth()) {
                items(quoteList.value.quotes.size) {
                    val quote = quoteList.value.quotes[it]
                    ListItem(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            navigator.navigate(
                                QuoteDetailScreenDestination(
                                    quote.quote,
                                    quote.author
                                )
                            )
                        }, quote.quote, quote.author
                    )
                }
            }
        }
    }
}




