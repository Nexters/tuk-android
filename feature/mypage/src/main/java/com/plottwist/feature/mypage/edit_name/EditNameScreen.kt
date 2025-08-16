package com.plottwist.feature.mypage.edit_name

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.plottwist.core.designsystem.component.TukTopAppBar
import com.plottwist.core.designsystem.component.TukTopAppBarType
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
fun EditNameScreen(
    onBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: EditNameViewModel = hiltViewModel(),
) {
    val state by viewModel.collectAsState()

    viewModel.collectSideEffect {
        when (it) {
            EditNameSideEffect.NavigateBack -> {
                onBack()
            }
            EditNameSideEffect.SaveSuccess -> {

            }

            is EditNameSideEffect.ShowToast -> {

            }
        }
    }

    EditNameScreen(
        modifier = modifier,
        name = state.name,
        onBackClick = { viewModel.handleAction(EditNameAction.ClickBack) },
        onNameChanged = { viewModel.handleAction(EditNameAction.OnNameChanged(it)) },
        onSaveClick = { viewModel.handleAction(EditNameAction.ClickSave) }
    )
}

@Composable
private fun EditNameScreen(
    name: String,
    onBackClick: () -> Unit,
    onNameChanged: (String) -> Unit,
    onSaveClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize()
    ) {
        EditNameAppBar(onBackClick = onBackClick)
        // TODO: Add content for editing name (e.g., TextField)
    }
}

@Composable
fun EditNameAppBar(
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    TukTopAppBar(
        modifier = modifier,
        type = TukTopAppBarType.DEPTH,
        title = "이름 변경",
        onBack = onBackClick
    )
}

@Preview
@Composable
private fun EditNameScreenPreview() {
    EditNameScreen(
        name = "테스트 이름",
        onBackClick = {},
        onNameChanged = {},
        onSaveClick = {}
    )
}
