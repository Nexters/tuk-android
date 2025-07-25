package com.example.create_gathering.page

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.create_gathering.model.GatheringHashTag

@Composable
fun CreateGatheringSelectTags(
    selectedTags: List<GatheringHashTag>,
    onToggle: (GatheringHashTag) -> Unit,
    onAddTag: (GatheringHashTag) -> Unit
) {
    var showDialog by remember { mutableStateOf(false) }

    if (showDialog) {
        AddTagDialog(
            onDismiss = { showDialog = false },
            onConfirm = {
                // 예시: 새 태그 추가할 때 고유 ID 생성
                val newId = (selectedTags.maxOfOrNull { it.id } ?: 0) + 1
                onAddTag(GatheringHashTag(id = newId, title = it))
                showDialog = false
            }
        )
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp, vertical = 48.dp)
    ) {
        Text(
            text = "마지막으로 모임에 대해 알려주세요",
            style = MaterialTheme.typography.headlineMedium
        )
        Spacer(modifier = Modifier.height(12.dp))
        Text(
            text = "만남을 가지면 주로 무엇을 하나요",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(24.dp))

        val allTags = listOf(
            GatheringHashTag(1, "TAG"),
            GatheringHashTag(2, "TAG"),
            GatheringHashTag(3, "TAG TEXT"),
            GatheringHashTag(4, "TAG"),
            GatheringHashTag(5, "TAG"),
            GatheringHashTag(6, "TAG"),
            GatheringHashTag(7, "TAG"),
            GatheringHashTag(8, "TAG")
        )

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            allTags.forEach { tag ->
                val isSelected = selectedTags.any { it.id == tag.id }

                FilterChip(
                    selected = isSelected,
                    onClick = { onToggle(tag) },
                    label = {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = tag.title)
                            if (isSelected) {
                                Spacer(modifier = Modifier.width(4.dp))
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Remove tag",
                                    modifier = Modifier.size(14.dp)
                                )
                            }
                        }
                    },
                    colors = FilterChipDefaults.filterChipColors(
                        containerColor = if (isSelected) Color.Black else Color(0xFFF4F4F4),
                        labelColor = if (isSelected) Color.White else Color.Black,
                        selectedContainerColor = Color.Black,
                        selectedLabelColor = Color.White,
                        selectedTrailingIconColor = Color.White
                    )
                )
            }

            // + 버튼
            FilterChip(
                selected = false,
                onClick = { showDialog = true },
                label = { Text(text = "+") }
            )
        }
    }
}


@Composable
fun AddTagDialog(
    onDismiss: () -> Unit,
    onConfirm: (String) -> Unit
) {
    var input by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = onDismiss,
        confirmButton = {
            TextButton(
                onClick = {
                    if (input.isNotBlank()) {
                        onConfirm(input.trim())
                    }
                }
            ) {
                Text("확인")
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text("취소")
            }
        },
        title = { Text("태그 추가") },
        text = {
            OutlinedTextField(
                value = input,
                onValueChange = { input = it },
                placeholder = { Text("예: 운동, 스터디 등") },
                singleLine = true
            )
        }
    )
}
