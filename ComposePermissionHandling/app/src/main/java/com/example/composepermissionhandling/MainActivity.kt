package com.example.composepermissionhandling

import android.os.Bundle
import android.Manifest
import android.app.LocaleConfig
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import com.example.composepermissionhandling.ui.theme.ComposePermissionHandlingTheme
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.rememberMultiplePermissionsState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPermissionsApi::class, ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposePermissionHandlingTheme {
                val permissionState = rememberMultiplePermissionsState(
                    permissions = listOf(
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.CAMERA,
                    )
                )
                val lifeCycleOwner = LocalLifecycleOwner.current
                DisposableEffect(key1 = lifeCycleOwner, effect = {
                    val observer = LifecycleEventObserver { _, event ->
                        if (event == Lifecycle.Event.ON_START)
                            permissionState.launchMultiplePermissionRequest()
                    }
                    lifeCycleOwner.lifecycle.addObserver(observer)
                    onDispose {
                        lifeCycleOwner.lifecycle.removeObserver(observer)
                    }


                })

                Scaffold(
                    topBar = {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(color = MaterialTheme.colorScheme.primary)
                                .padding(12.dp), contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Permission Handling",
                                fontSize = 30.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.White
                            )
                        }

                    }
                ) {
                    Surface(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(it),
                        color = MaterialTheme.colorScheme.background
                    ) {
                        Column(
                            modifier = Modifier.fillMaxSize(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            permissionState.permissions.forEach { perm ->
                                when (perm.permission) {
                                    Manifest.permission.CAMERA -> {
                                        when {
                                            perm.hasPermission -> {
                                                Text(text = "Camera permission accepted")
                                            }

                                            perm.shouldShowRationale -> {
                                                Text(
                                                    text = "Camera permission is needed" +
                                                            "to access the camera"
                                                )
                                            }

                                            perm.isPermanentlyDenied() -> {
                                                Text(
                                                    text = "Camera permission was permanently" +
                                                            "denied. You can enable it in the app" +
                                                            "settings."
                                                )
                                            }
                                        }
                                    }

                                    Manifest.permission.RECORD_AUDIO -> {
                                        when {
                                            perm.hasPermission -> {
                                                Text(text = "Record audio permission accepted")
                                            }

                                            perm.shouldShowRationale -> {
                                                Text(
                                                    text = "Record audio permission is needed" +
                                                            "to access the camera"
                                                )
                                            }

                                            perm.isPermanentlyDenied() -> {
                                                Text(
                                                    text = "Record audio permission was permanently" +
                                                            "denied. You can enable it in the app" +
                                                            "settings."
                                                )
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

