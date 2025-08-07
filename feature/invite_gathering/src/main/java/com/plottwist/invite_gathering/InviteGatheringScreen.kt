package com.plottwist.invite_gathering

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray100
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray800
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray900
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun InviteGatheringScreen(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InviteGatheringViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            InviteGatheringSideEffect.NavigateBack -> onBackClicked()
        }
    }

    InviteGatheringScreen(
        modifier = modifier,
        url = state.url,
        onBackClicked = { viewModel.handleAction(InviteGatheringAction.ClickBack) }
    )
}

@Composable
private fun InviteGatheringScreen(
    url: String,
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(horizontal = 20.dp)
    ) {
        InviteGatheringAppBar(onBackClicked = onBackClicked)

        Text(
            modifier = modifier.padding(top = 20.dp),
            text = stringResource(R.string.invite_gathering_description),
            style = TukPretendardTypography.body14R,
            color = Gray800
        )
        InviteGatheringLink(
            modifier = Modifier.padding(top = 15.dp),
            url = url
        )
        
    }
}

@Composable
fun InviteGatheringAppBar(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    TukTopAppBar(
        modifier = modifier,
        type = TukTopAppBarType.DEPTH,
        title = stringResource(R.string.invite_gathering_topbar_title),
        onBack = onBackClicked,
    )
}

@Composable
fun InviteGatheringLink(
    url: String,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier.fillMaxWidth()
            .background(
                color = Gray100,
                shape = RoundedCornerShape(20.dp)
            ).padding(vertical = 34.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = url,
            style = TukPretendardTypography.body12R,
            color = Gray900
        )
    }
}

@Preview
@Composable
private fun InviteGatheringScreenPreview() {
    InviteGatheringScreen(
        onBackClicked = {}
    )
}
