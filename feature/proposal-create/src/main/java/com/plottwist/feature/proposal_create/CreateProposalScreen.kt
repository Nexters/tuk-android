package com.plottwist.feature.proposal_create

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.plottwist.feature.proposal_create.component.CreateProposalPostCard
import org.orbitmvi.orbit.compose.collectAsState

@Composable
fun CreateProposalScreen(
    modifier: Modifier = Modifier,
    viewModel: CreateProposalViewModel = hiltViewModel()
) {
    val state by viewModel.collectAsState()

    CreateProposalScreen(
        modifier = modifier,
        whereLabel = state.whereLabel,
        whenLabel = state.whenLabel,
        whatLabel = state.whatLabel,
        onCloseClicked = {
            viewModel.handleAction(CreateProposalAction.ClickClose)
        }
    )
}

@Composable
private fun CreateProposalScreen(
    whereLabel: String,
    whenLabel: String,
    whatLabel: String,
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

            CreateProposalPostCard(
                modifier = Modifier.padding(top = 66.dp)
                    .width(260.dp)
                    .height(370.dp)
                    .align(Alignment.CenterHorizontally),
                whereLabel = whereLabel,
                whenLabel = whenLabel,
                whatLabel = whatLabel,
                onSelectGatheringClick = {}
            )
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
        whereLabel = "where",
        whenLabel = "when",
        whatLabel = "what",
        onCloseClicked = {}
    )
}
