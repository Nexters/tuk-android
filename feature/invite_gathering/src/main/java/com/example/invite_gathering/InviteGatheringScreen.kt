package com.example.invite_gathering

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.component.TukTextButton
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.tuk.feature.invite_gathering.R

@Composable
fun InviteGatheringScreen(
    onCloseClicked: () -> Unit

) {
    Box(
        modifier = Modifier
    ) {
        StableImage(
            modifier = Modifier
                .fillMaxWidth(),
            drawableResId = com.plottwist.core.designsystem.R.drawable.image_login_gradient
        )


        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            InviteGatheringAppBar(
                onCloseClicked = onCloseClicked
            )


            Text(
                modifier = Modifier.padding(horizontal = 20.dp),

                text = "가볍게 제안하고\n" +
                        "만남을 이어가세요",
                style = TukSerifTypography.title22M
            )

            InviteGatheringContent()

            Spacer(modifier = Modifier.weight(1f))
            InviteGatheringButton( onClick = {})

        }
    }
}

@Composable
fun InviteGatheringContent(
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(288f/329f)
            .clip(RoundedCornerShape(20.dp))
    ) {

        Image(
            painter = painterResource(id = R.drawable.image_invite_gathering_card),
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {


            Box(
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .border(
                        width = 1.dp,
                        color = Color(0xFFE0E0E0),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 4.dp)
            ) {


                StableImage(
                    drawableResId = R.drawable.image_invite_gathering_create_btn
                )
            }


            Column(
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(
                    text = "제주 여행가서 이야기 나누기 어때"
                )
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween

            ) {

                Text(
                    text = "연락이"
                )
                Text(
                    text = "뜸해진 우리"
                )

            }
        }

    }

}

@Composable
fun InviteGatheringButton(
    onClick: () -> Unit
) {
    TukTextButton(
        text = "넌지시 제안하기",
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 29.dp, bottom = 17.dp)
            .height(52.dp),
        onClick = {onClick()}
    )
}


@Composable
fun InviteGatheringAppBar(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    TukTopAppBar(
        modifier = Modifier,
        actionButtons = {
            TopAppBarCloseButton(
                onCloseClicked = onCloseClicked
            )
        }
    )
}

@Composable
fun TopAppBarCloseButton(
    onCloseClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    IconButton(
        modifier = modifier,
        onClick = onCloseClicked
    ) {
        StableImage(
            drawableResId = com.plottwist.core.designsystem.R.drawable.ic_close
        )
    }
}