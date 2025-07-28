package com.plottwist.feature.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.SolidButton
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.domain.model.Gatherings
import com.plottwist.feature.home.LoginState

@Composable
fun HomeContent(
    loginState: LoginState,
    gatherings: Gatherings,
    onAddGatheringClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    if(loginState == LoginState.Loading) return

    if(gatherings.totalCount == 0) {
        HomeCreateGatheringPreview(
            modifier = modifier,
            onAddGatheringClick = onAddGatheringClick
        )
    } else {

    }
}

@Composable
fun HomeCreateGatheringPreview(
    onAddGatheringClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(20.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.home_bottom_create_gathering_description),
            style = TukPretendardTypography.body14R,
            color = Color(0xFF888888),
            textAlign = TextAlign.Center
        )
        SolidButton(
            text = stringResource(R.string.home_bottom_create_gathering_button_text),
            containerColor = Color(0xFFFF3838),
            contentColor = Color(0xFFFFFFFF),
            onClick = onAddGatheringClick
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                contentDescription = stringResource(R.string.home_bottom_create_gathering_button_text),
                tint = Color(0xFFFFFFFF)
            )
        }
    }
}
