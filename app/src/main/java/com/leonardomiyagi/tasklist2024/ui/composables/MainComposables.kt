package com.leonardomiyagi.tasklist2024.ui.composables

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import com.leonardomiyagi.tasklist2024.R
import com.leonardomiyagi.tasklist2024.data.local.Task

@Composable
fun TaskCard(task: Task) {
    Text(
        text = task.title,
        color = MaterialTheme.colorScheme.primary,
        style = MaterialTheme.typography.titleLarge
    )
    Text(
        text = task.description ?: stringResource(R.string.global_no_description),
        color = MaterialTheme.colorScheme.secondary,
        style = MaterialTheme.typography.titleSmall,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
}