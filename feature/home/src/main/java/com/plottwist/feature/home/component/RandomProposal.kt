package com.plottwist.feature.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.component.TukRoundSolidButton
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.StableImage

@Composable
fun RandomProposal(
    whenLabel: String,
    whereLabel: String,
    whatLabel: String,
    onWhenRefreshClick: () -> Unit,
    onWhereRefreshClick: () -> Unit,
    onWhatRefreshClick: () -> Unit,
    onProposeClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = stringResource(R.string.home_random_proposal_description_prefix),
            style = TukSerifTypography.body16M.copy(
                fontWeight = FontWeight.Normal
            )
        )

        RandomProposalItem(
            label = whereLabel,
            onRefreshClick = onWhereRefreshClick
        )

        RandomProposalItem(
            label = whenLabel,
            onRefreshClick = onWhenRefreshClick
        )

        RandomProposalItem(
            label = whatLabel,
            onRefreshClick = onWhatRefreshClick
        )

        Text(
            text = stringResource(R.string.home_random_proposal_description_suffix),
            style = TukSerifTypography.body16M.copy(
                fontWeight = FontWeight.Normal
            )
        )

        Spacer(modifier = Modifier.weight(1f))

        TukRoundSolidButton(
            modifier = Modifier.padding(bottom = 30.dp),
            text = stringResource(R.string.home_bottom_sheet_nudging_text),
            containerColor = Color(0xFFFF3838),
            contentColor = Color(0xFFFFFFFF),
            onClick = onProposeClick
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_add_circle),
                contentDescription = "propose",
                tint = Color(0xFFFFFFFF)
            )
        }
    }
}

@Composable
fun RandomProposalItem(
    label: String,
    onRefreshClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        modifier = modifier
            .width(264.dp)
            .height(52.dp)
            .background(
                color = Color(0xFFEaEaea),
                shape = RoundedCornerShape(10.dp)
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onRefreshClick()
            }
            .padding(
                horizontal = 20.dp,
                vertical = 15.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        StableImage(
            modifier = Modifier
                .size(20.dp)
            ,
            drawableResId = R.drawable.ic_refresh
        )
        Text(
            text = label,
            style = TukSerifTypography.body16M
        )
    }
}

