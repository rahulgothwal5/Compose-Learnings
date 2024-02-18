package com.example.composebottomsheetandnavdrawer

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import c.AppBar
import com.example.composebottomsheetandnavdrawer.ui.DrawerBody
import com.example.composebottomsheetandnavdrawer.ui.DrawerHeader
import com.example.composebottomsheetandnavdrawer.ui.MyNavDrawer
import com.example.composebottomsheetandnavdrawer.ui.theme.ComposeBottomSheetandNavDrawerTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeBottomSheetandNavDrawerTheme {
                val sheetState = rememberModalBottomSheetState()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)

                val scope = rememberCoroutineScope()
                var showBottomSheet by remember { mutableStateOf(false) }

                // A surface container using the 'background' color from the theme
                MyNavDrawer(drawerState,{
                    DrawerHeader()
                    DrawerBody(
                        items = listOf(
                            MenuItem(
                                id = "home",
                                title = "Home",
                                contentDescription = "Go to home screen",
                                icon = Icons.Default.Home
                            ),
                            MenuItem(
                                id = "settings",
                                title = "Settings",
                                contentDescription = "Go to settings screen",
                                icon = Icons.Default.Settings
                            ),
                            MenuItem(
                                id = "help",
                                title = "Help",
                                contentDescription = "Get help",
                                icon = Icons.Default.Info
                            ),
                        ),
                        onItemClick = {
                            println("Clicked on ${it.title}")
                        },
                    )

                }) {
                    Scaffold(topBar = {
                        AppBar {
                            scope.launch {
                                drawerState.apply {
                                    if (isClosed) open() else close()
                                }
                            }
                        }
                    }) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Button(onClick = {
                                showBottomSheet = true
                            }) {
                                Text(text = "Bottom sheet fractio: ${sheetState.currentValue.ordinal.dp}")
                            }
                        }

                        if (showBottomSheet) {
                            MyBottomSheet(sheetState, scope) {
                                showBottomSheet = it
                            }
                        }
                    }
                }


            }
        }
    }

    @Composable
    @OptIn(ExperimentalMaterial3Api::class)
    fun MyBottomSheet(
        sheetState: SheetState,
        scope: CoroutineScope,
        onClosure: (Boolean) -> Unit
    ) {
        ModalBottomSheet(
            onDismissRequest = {
                onClosure(false)
            },
            sheetState = sheetState
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Bottom sheet",
                    fontSize = 60.sp
                )
                Button(onClick = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            onClosure(false)
                        }
                    }
                }) {
                    Text("Hide bottom sheet")
                }
            }
        }
    }
}

