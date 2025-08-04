package com.plottwist.core.designsystem.component

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography

@Composable
fun TukTopAppBar(
    modifier: Modifier = Modifier,
    type: TukTopAppBarType = TukTopAppBarType.PLAIN,
    title: String = "",
    actionButtons: (@Composable RowScope.() -> Unit)? = null,
    onBack: (() -> Unit)? = null
) {
    Row(
        modifier = modifier
            .statusBarsPadding()
            .height(64.dp)
            .fillMaxWidth()
            .padding(
                start = if(type == TukTopAppBarType.PLAIN) 20.dp else 12.dp,
                end = if(actionButtons == null) 20.dp else 12.dp
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if(type == TukTopAppBarType.DEPTH) {
            TopAppBarBackButton(
                onBackClicked = {
                    onBack?.invoke()
                }
            )
        }
        if(title.isNotBlank()) {
            Text(
                text = title,
                style = TukSerifTypography.body16M.copy(
                    fontSize = 18.sp
                )
            )
        }

        Spacer(modifier = Modifier.weight(1f))
        actionButtons?.invoke(this)
    }
}

@Composable
fun TopAppBarBackButton(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onBackClicked
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(R.drawable.ic_back_arrow),
            contentDescription = "back"
        )
    }
}


enum class TukTopAppBarType {
    DEPTH,
    PLAIN
}
