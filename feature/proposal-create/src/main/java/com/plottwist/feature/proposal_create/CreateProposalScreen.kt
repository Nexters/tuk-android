package com.plottwist.feature.proposal_create

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.core.ui.component.TopAppBarCloseButton

@Composable
fun CreateProposalScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateProposalViewModel = hiltViewModel()
) {
    CreateProposalScreen(
        modifier = modifier,
        onCloseClicked = {
            viewModel.handleAction(CreateProposalAction.ClickClose)
        }
    )
}

@Composable
private fun CreateProposalScreen(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box (
         modifier = modifier
    ) {
        CreateProposalGradientBackgroundImage(
            modifier = Modifier.align(Alignment.Center)
        )

        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            CreateProposalAppBar(onCloseClicked = onCloseClicked)

            CreateProposalTitle()


        }
    }
}

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun CreateProposalGradientBackgroundImage(
    modifier: Modifier = Modifier
) {
    val localConfiguration = LocalConfiguration.current
    StableImage(
        modifier = modifier
            .requiredWidth(
                localConfiguration.screenWidthDp.dp * GRADIENT_BACKGROUND_IMAGE_SCALE
            ),
        contentScale = ContentScale.Crop,
        drawableResId = R.drawable.image_proposal_gradient
    )
}

@Composable
fun CreateProposalAppBar(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier,
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

@Composable
fun CreateProposalTitle(
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier.padding(start = 20.dp),
        text = stringResource(R.string.create_proposal_title),
        style = TukSerifTypography.title24M
    )
}

private const val GRADIENT_BACKGROUND_IMAGE_SCALE = 3

@Preview
@Composable
private fun CreateProposalScreenPreview() {
    CreateProposalScreen(
        onCloseClicked = {}
    )
}
