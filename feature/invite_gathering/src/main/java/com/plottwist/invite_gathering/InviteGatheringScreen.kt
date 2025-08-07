package com.plottwist.invite_gathering

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalClipboard
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray000
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray100
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray800
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray900
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import androidx.core.net.toUri

@Composable
fun InviteGatheringScreen(
    onBackClicked: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InviteGatheringViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()
    val context = LocalContext.current
    val clipboardManager = LocalClipboard.current
   // val clipboardManager = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    val contentResolver = context.contentResolver

    viewModel.collectSideEffect { sideEffect ->
        when (sideEffect) {
            InviteGatheringSideEffect.NavigateBack -> {
                onBackClicked()
            }
            is InviteGatheringSideEffect.CopyToClipboard -> {
                clipboardManager.nativeClipboard.setPrimaryClip(
                    ClipData.newPlainText("URI", sideEffect.text)
                )
            }
            is InviteGatheringSideEffect.ShareContent -> {
                shareUrl(context, sideEffect.text)
            }
        }
    }

    InviteGatheringScreen(
        modifier = modifier,
        url = state.url,
        onCopyClick = { viewModel.handleAction(InviteGatheringAction.ClickCopy) },
        onShareClick = { viewModel.handleAction(InviteGatheringAction.ClickShare) },
        onBackClicked = { viewModel.handleAction(InviteGatheringAction.ClickBack) }
    )
}

@Composable
private fun InviteGatheringScreen(
    url: String,
    onBackClicked: () -> Unit,
    onCopyClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        InviteGatheringAppBar(onBackClicked = onBackClicked)

        Text(
            modifier = modifier
                .padding(top = 20.dp)
                .padding(horizontal = 20.dp),
            text = stringResource(R.string.invite_gathering_description),
            style = TukPretendardTypography.body14R,
            color = Gray800
        )
        InviteGatheringLink(
            modifier = Modifier
                .padding(top = 15.dp)
                .padding(horizontal = 20.dp),
            url = url,
            onCopyClick = onCopyClick,
            onShareClick = onShareClick
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
    onCopyClick: () -> Unit,
    onShareClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Gray100,
                shape = RoundedCornerShape(20.dp)
            )
            .padding(vertical = 34.dp, horizontal = 8.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = url,
            style = TukPretendardTypography.body12R,
            color = Gray900
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(7.dp, Alignment.CenterHorizontally)
        ) {
            OutlinedText(
                text = stringResource(R.string.invite_gathering_copy),
                onClick = onCopyClick
            )

            OutlinedText(
                text = stringResource(R.string.invite_gathering_share),
                onClick = onShareClick
            )
        }
    }
}

@Composable
fun OutlinedText(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .background(
                color = Gray000,
                shape = CircleShape
            )
            .clip(CircleShape)
            .border(
                width = 1.dp,
                shape = CircleShape,
                color = Gray900
            )
            .clickable {
                onClick()
            }
            .padding(vertical = 8.dp, horizontal = 24.dp),
        text = text,
        style = TukPretendardTypography.body14M,
        color = Gray900
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
private fun InviteGatheringScreenPreview() {
    InviteGatheringScreen(
        onBackClicked = {}
    )
}
