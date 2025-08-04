package com.plottwist.feature.gathering_detail.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.foundation.type.TukPretendardTypography
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.domain.model.GatheringMember

@Composable
fun GatheringMembers(
    members: List<GatheringMember>,
    onInviteMemberClick : () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFF5F5F5),
                shape = RoundedCornerShape(20.dp)
            )
            .padding(horizontal = 20.dp, vertical = 15.dp)
            .padding(bottom = 5.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        InvitationMemberText(
            onInviteMemberClick = onInviteMemberClick
        )
        HorizontalDivider(
            color = Color(0xFFEFEFEF)
        )

        Text(
            modifier = Modifier.padding(top = 5.dp),
            text = stringResource(R.string.gathering_detail_member, members.size),
            style = TukSerifTypography.body14R,
            color = Color(0xFF888888)
        )

        members.forEach { member ->
            Text(
                text = member.memberName,
                style = TukPretendardTypography.body16R,
                color = Color(0xFF1f1f1f)
            )
        }
    }
}


@Composable
fun InvitationMemberText(
    onInviteMemberClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(30.dp)
            .clip(RoundedCornerShape(20.dp))
            .clickable(
                interactionSource = null,
                indication = null
            ) {
                onInviteMemberClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(2.dp, Alignment.CenterHorizontally)
    ) {
        Icon(
            modifier = Modifier.size(18.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_add),
            contentDescription = "add"
        )

        Text(
            text = "멤버 초대",
            style = TukPretendardTypography.body14R,
            color = Color(0xFF1F1F1F)
        )
    }
}

