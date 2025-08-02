package com.plottwist.feature.proposal_create.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.PostCard
import com.plottwist.core.ui.component.StableImage
import com.plottwist.core.ui.extension.dashedBorder

@Composable
fun CreateProposalPostCard(
    whereLabel: String,
    whenLabel: String,
    whatLabel: String,
    onSelectGatheringClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    PostCard(
        modifier = modifier,
        paddingValues = PaddingValues(
            top = 12.dp,
            bottom = 15.dp,
            start = 15.dp,
            end = 15.dp
        )
    ) {
        SelectGatheringButton(
            modifier = Modifier.fillMaxWidth(),
            onClick = onSelectGatheringClick
        )

        ProposalContent(
            modifier = Modifier.weight(1f).padding(start = 26.dp),
            whereLabel = whereLabel,
            whenLabel = whenLabel,
            whatLabel = whatLabel
        )
    }
}

@Composable
fun SelectGatheringButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .dashedBorder(
                color = Color(0xFFA0A0A0),
                shape = RoundedCornerShape(10.dp),
                strokeWidth = 1.dp
            )
            .height(32.dp)
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onClick()
            },
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            modifier = Modifier.size(20.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_add),
            contentDescription = "add",
            tint = Color(0xFF888888)
        )

        Text(
            text = stringResource(R.string.create_proposal_select_gathering),
            style = TukPretendardTypography.body12R,
            color = Color(0xFF888888)
        )
    }
}

@Composable
fun ProposalContent(
    whereLabel: String,
    whenLabel: String,
    whatLabel: String,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.Center
    ) {
        StableImage(
            drawableResId = R.drawable.image_double_quotation_marks
        )
        Text(
            modifier = Modifier.padding(top = 15.dp),
            text = whereLabel,
            style = TukSerifTypography.body16M
        )
        Text(
            text = whenLabel,
            style = TukSerifTypography.body16M
        )
        Text(
            modifier = Modifier.padding(bottom = 15.dp),
            text = whatLabel,
            style = TukSerifTypography.body16M
        )
        Text(
            text = stringResource(R.string.home_random_proposal_description_suffix),
            style = TukSerifTypography.body16M
        )
    }
}

@Preview
@Composable
private fun CreateProposalPostCardPreview() {
    CreateProposalPostCard(
        whereLabel = "where",
        whenLabel = "when",
        whatLabel = "what",
        onSelectGatheringClick = {}
    )
}
