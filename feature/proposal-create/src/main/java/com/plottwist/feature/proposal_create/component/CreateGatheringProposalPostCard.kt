package com.plottwist.feature.proposal_create.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.KeyboardActionHandler
import androidx.compose.foundation.text.input.OutputTransformation
import androidx.compose.foundation.text.input.TextFieldBuffer
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.plottwist.core.designsystem.R
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray500
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray800
import com.plottwist.core.designsystem.foundation.TukColorTokens.Gray900
import com.plottwist.core.designsystem.foundation.type.TukSerifTypography
import com.plottwist.core.ui.component.StableImage
import com.plottwist.core.ui.extension.dropShadow

@Composable
fun CreateGatheringProposalPostCard(
    whereLabel: TextFieldState,
    whenLabel: TextFieldState,
    whatLabel: TextFieldState,
    onWhenRefreshClick: () -> Unit,
    onWhereRefreshClick: () -> Unit,
    onWhatRefreshClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var editMode by remember { mutableStateOf(false) }

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(editMode) {
        if(editMode) {
            focusRequester.requestFocus()
        }
    }

    Column(
        modifier = modifier
            .clip(RoundedCornerShape(10.dp))
            .border(
                width = 1.dp,
                color = Color(0xFF000000).copy(0.05f),
                shape = RoundedCornerShape(10.dp)
            )
            .background(
                color = Color(0xFFF0F1F3),
                shape = RoundedCornerShape(10.dp)
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Box(
            modifier = modifier.fillMaxWidth()
        ) {
            if(!editMode) {
                EditButton(
                    modifier = Modifier.align(Alignment.TopEnd).padding(15.dp),
                    onClick = {
                        editMode = !editMode
                    }
                )
            } else {
                DoneButton(
                    modifier = Modifier.align(Alignment.TopEnd).padding(15.dp),
                    onClick = {
                        editMode = !editMode
                    }
                )
            }
            RandomProposal(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 46.dp,
                        bottom = 43.dp,
                        start = 24.dp,
                        end = 24.dp
                    ),
                whenLabel = whenLabel,
                whereLabel = whereLabel,
                whatLabel = whatLabel,
                focusRequester = focusRequester,
                onWhenRefreshClick = onWhenRefreshClick,
                onWhereRefreshClick = onWhereRefreshClick,
                onWhatRefreshClick = onWhatRefreshClick,
                editMode = editMode,
                onEditClick = {
                    editMode = !editMode
                },
                onKeyboardAction = {
                    editMode = false
                    focusRequester.freeFocus()
                }
            )
        }

    }
}

@Composable
fun RandomProposal(
    whenLabel: TextFieldState,
    whereLabel: TextFieldState,
    whatLabel: TextFieldState,
    editMode: Boolean,
    focusRequester: FocusRequester,
    onWhenRefreshClick: () -> Unit,
    onWhereRefreshClick: () -> Unit,
    onWhatRefreshClick: () -> Unit,
    onKeyboardAction: () -> Unit,
    onEditClick: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Text(
            text = stringResource(R.string.home_random_proposal_description_prefix),
            style = TukSerifTypography.body14R.copy(
                fontWeight = FontWeight.Medium
            ),
            color = Gray800
        )

        RandomProposalItem(
            label = whereLabel,
            isEditMode = editMode,
            focusRequester = focusRequester,
            onRefreshClick = onWhereRefreshClick,
            onKeyboardAction = onKeyboardAction
        )

        RandomProposalItem(
            label = whenLabel,
            isEditMode = editMode,
            onRefreshClick = onWhenRefreshClick,
            onKeyboardAction = onKeyboardAction
        )

        RandomProposalItem(
            label = whatLabel,
            isEditMode = editMode,
            onRefreshClick = onWhatRefreshClick,
            onKeyboardAction = onKeyboardAction
        )

        Text(
            text = stringResource(R.string.home_random_proposal_description_suffix),
            style = TukSerifTypography.body14R.copy(
                fontWeight = FontWeight.Medium
            ),
            color = Gray800
        )
    }
}

@Composable
fun RandomProposalItem(
    label: TextFieldState,
    isEditMode: Boolean,
    onRefreshClick: () -> Unit,
    onKeyboardAction: () -> Unit,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = remember { FocusRequester() },
) {
    Row(
        modifier = modifier
            .padding(top = 7.dp)
            .width(264.dp)
            .height(52.dp)
            .background(
                color = Color(0xFFEaEaea),
                shape = RoundedCornerShape(10.dp)
            )
            .then(
                if (isEditMode) Modifier.border(
                    width = 1.dp,
                    color = Gray900,
                    shape = RoundedCornerShape(10.dp)
                ) else Modifier
            )
            .clip(RoundedCornerShape(10.dp))
            .clickable {
                onRefreshClick()
            }
            .padding(
                horizontal = if (isEditMode) 0.dp else 20.dp,
                vertical = if (isEditMode) 0.dp else 15.dp
            ),
        horizontalArrangement = Arrangement.spacedBy(5.dp, Alignment.CenterHorizontally),
        verticalAlignment = Alignment.CenterVertically
    ) {
        if (isEditMode) {
            ProposalTextField(
                state = label,
                hint = "직접 입력",
                modifier = Modifier.weight(1f),
                focusRequester = focusRequester,
                onKeyboardAction = onKeyboardAction
            )
        } else {
            StableImage(
                modifier = Modifier
                    .size(20.dp),
                drawableResId = R.drawable.ic_refresh
            )
            Text(
                text = label.text.toString(),
                style = TukSerifTypography.body16M
            )
        }
    }
}

@Composable
fun EditButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .dropShadow(CircleShape)
            .background(
                color = Color.White,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .padding(5.dp)
    ) {
        Icon(
            modifier = Modifier.size(14.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_edit),
            contentDescription = "edit"
        )
    }
}

@Composable
fun DoneButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .dropShadow(CircleShape)
            .background(
                color = Color.Black,
                shape = CircleShape
            )
            .clip(CircleShape)
            .clickable {
                onClick()
            }
            .padding(5.dp)
    ) {
        Icon(
            modifier = Modifier.size(14.dp),
            imageVector = ImageVector.vectorResource(R.drawable.ic_check),
            contentDescription = "check",
            tint = Color.White
        )
    }
}

@Composable
fun ProposalTextField(
    state: TextFieldState,
    hint: String,
    modifier: Modifier = Modifier,
    focusRequester: FocusRequester = remember { FocusRequester() },
    onFocus: (Boolean) -> Unit = {},
    onKeyboardAction: () -> Unit,
) {
    BasicTextField(
        modifier = modifier
            .background(
                Color.White
            )
            .focusRequester(focusRequester)
            .onFocusChanged { focusState -> onFocus(focusState.isFocused) }
            .padding(horizontal = 20.dp),
        state = state,
        lineLimits = TextFieldLineLimits.SingleLine,
        textStyle = TukSerifTypography.body16M.copy(
            color = if (state.text.isNotBlank()) Gray900 else Gray500,
            textAlign = TextAlign.Center
        ),
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done
        ),
        onKeyboardAction = KeyboardActionHandler {
            onKeyboardAction()
        },
        outputTransformation = remember {
            object : OutputTransformation {
                override fun TextFieldBuffer.transformOutput() {

                    if (this.originalText.isEmpty()) {
                        replace(0, 0, "직접 입력")
                    }
                }
            }
        },
        decorator = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
//                if (state.text.isEmpty()) {
//                    Text(
//                        text = hint,
//                        color = Gray500,
//                        style = TukSerifTypography.body16M
//                    )
//                }
                innerTextField()
            }
        }
    )
}

@Preview
@Composable
private fun CreateGatheringProposalPostCardPreview() {
    CreateGatheringProposalPostCard(
        whereLabel = TextFieldState("가 다라마바사아자타"),
        whenLabel = TextFieldState(),
        whatLabel = TextFieldState(),
        onWhenRefreshClick = {},
        onWhereRefreshClick = {},
        onWhatRefreshClick = { },
    )
}
