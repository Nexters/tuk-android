package com.plottwist.core.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.FabPosition
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray000
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray800
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray900
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography

@Composable
fun TukScaffold(
    modifier: Modifier = Modifier,
    title: String = "",
    description: String = "",
    topBar: @Composable () -> Unit = {},
    bottomBar: @Composable () -> Unit = {},
    snackbarHost: @Composable () -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    floatingActionButtonPosition: FabPosition = FabPosition.End,
    containerColor: Color = Gray000,
    contentColor: Color = contentColorFor(containerColor),
    contentWindowInsets: WindowInsets = WindowInsets.navigationBars,
    contentPaddingValues: PaddingValues = PaddingValues(
        horizontal = 20.dp,
        vertical = 10.dp
    ),
    listState: LazyListState = rememberLazyListState(),
    content: LazyListScope.() -> Unit
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            .imePadding(),
        topBar = topBar,
        bottomBar = bottomBar,
        snackbarHost = snackbarHost,
        floatingActionButton = floatingActionButton,
        floatingActionButtonPosition = floatingActionButtonPosition,
        containerColor = containerColor,
        contentColor = contentColor,
        contentWindowInsets = contentWindowInsets,
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = contentPaddingValues,
            state = listState
        ) {
            if (title.isNotBlank()) {
                item {
                    TukScaffoldTitle(title = title)
                }
            }

            if (description.isNotBlank()) {
                item {
                    TukScaffoldDescription(
                        modifier = Modifier.padding(bottom = 48.dp),
                        description = description
                    )
                }
            }

            content()
        }
    }
}

@Composable
fun TukScaffoldTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier,
        text = title,
        style = TukSerifTypography.title24M,
        color = Gray900
    )
}

@Composable
fun TukScaffoldDescription(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(top = 24.dp),
        text = description,
        style = TukPretendardTypography.body14R,
        color = Gray800
    )
}
