package com.example.composeparallaxscroll

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.composeparallaxscroll.ui.theme.ComposeParallaxScrollTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ComposeParallaxScrollTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val imageHeight = (LocalConfiguration.current.screenWidthDp * (2f / 3f)).dp
                    val moonScaleSpeed = 0.08f
                    val midBgScaleSpeed = 0.03f

                    val lazyListState = rememberLazyListState()

                    var moonOffset by remember {
                        mutableStateOf(0f)
                    }

                    var midBGOffset by remember {
                        mutableStateOf(0f)
                    }

                    val nestedScrollConnection = object : NestedScrollConnection {
                        override fun onPreScroll(
                            available: Offset,
                            source: NestedScrollSource
                        ): Offset {
                            val delta = available.y
                            val layoutInfo = lazyListState.layoutInfo

                            if (lazyListState.firstVisibleItemIndex == 0)
                                return Offset.Zero

                            if (layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1)
                                return Offset.Zero

                            moonOffset += delta * moonScaleSpeed
                            midBGOffset += delta * midBgScaleSpeed
                            return return Offset.Zero
                        }

                    }

                    LazyColumn(
                        modifier = Modifier
                            .fillMaxWidth()
                            .nestedScroll(nestedScrollConnection),
                        state = lazyListState
                    )
                    {
                        items(10) {
                            Text("Sample item $it", modifier = Modifier.padding(5.dp))
                        }
                        item {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(imageHeight)
                                    .clipToBounds()
                                    .background(
                                        Brush.verticalGradient(
                                            listOf(
                                                Color(0xFFf36b21),
                                                Color(0xFFf9a521)
                                            )
                                        )
                                    )
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.ic_moonbg),
                                    contentDescription = "moon",
                                    contentScale = ContentScale.FillWidth,
                                    alignment = Alignment.BottomCenter,
                                    modifier = Modifier
                                        .matchParentSize()
                                        .graphicsLayer { translationY = moonOffset }
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_midbg),
                                    contentDescription = "mid bg",
                                    contentScale = ContentScale.FillWidth,
                                    alignment = Alignment.BottomCenter,
                                    modifier = Modifier
                                        .matchParentSize()
                                        .graphicsLayer { translationY = midBGOffset }
                                )
                                Image(
                                    painter = painterResource(id = R.drawable.ic_outerbg),
                                    contentDescription = "outer bg",
                                    contentScale = ContentScale.FillWidth,
                                    alignment = Alignment.BottomCenter,
                                    modifier = Modifier.matchParentSize()
                                )
                            }
                        }
                        items(20) {
                            Text("New item $it", modifier = Modifier.padding(5.dp))
                        }
                    }

                }
            }
        }
    }
}

