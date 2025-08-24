package com.plottwist.feature.proposal_create.complete

import android.content.Context
import android.content.Intent
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray100
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.TopAppBarCloseButton
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun CompleteProposeScreen(
    navigateToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: CompleteProposeViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()
    val context = LocalContext.current

    BackHandler {
        navigateToHome()
    }

    viewModel.collectSideEffect {
        when (it) {
            CompleteProposeSideEffect.NavigateToHome -> navigateToHome()
        }
    }

    CompleteProposeScreen(
        modifier = modifier,
        url = state.url,
        onCloseClicked = { viewModel.handleAction(CompleteProposeAction.ClickClose) },
        onShareClick = {
            shareUrl(context,state.url)
        }
    )
}

@Composable
private fun CompleteProposeScreen(
    url : String,
    onCloseClicked: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxSize().padding(bottom = 80.dp)
    ) {
        CompleteProposeAppBar(onCloseClicked = onCloseClicked)

        Column (
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp, Alignment.CenterVertically)
        ) {
            Text(
                text = "초대장이 링크로\n생성되었어요",
                style = TukSerifTypography.title22M,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier.padding(bottom = 6.dp),
                text = "지금 바로 간편하게 공유해 보세요",
                style = TukPretendardTypography.body14R
            )

            Row (
                modifier = modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .background(
                        color = Gray100,
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(vertical = 21.dp, horizontal = 14.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = url,
                    style = TukPretendardTypography.body12R
                )

                Text(
                    modifier = Modifier.clickable { onShareClick() },
                    text = "공유하기",
                    style = TukPretendardTypography.body12R,
                    textDecoration = TextDecoration.Underline
                )
            }
        }
    }
}

@Composable
fun CompleteProposeAppBar(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    TukTopAppBar(
        modifier = modifier,
        actionButtons = {
            TopAppBarCloseButton(
                onCloseClicked = onCloseClicked
            )
        }
    )
}

fun shareUrl(context: Context, url: String) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, url)
        type = "text/plain"
    }

    context.startActivity(Intent.createChooser(sendIntent, null))
}

@Preview
@Composable
private fun CompleteProposeScreenPreview() {
    CompleteProposeScreen(
        url = "",
        onCloseClicked = {},
        onShareClick = {}
    )
}
