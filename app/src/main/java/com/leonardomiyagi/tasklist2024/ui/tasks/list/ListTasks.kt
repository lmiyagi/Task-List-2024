package com.leonardomiyagi.tasklist2024.ui.tasks.list

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leonardomiyagi.tasklist2024.ui.composables.DefaultError
import com.leonardomiyagi.tasklist2024.ui.composables.DefaultFAB
import com.leonardomiyagi.tasklist2024.ui.composables.DefaultLoading
import com.leonardomiyagi.tasklist2024.ui.composables.DefaultTopBar
import com.leonardomiyagi.tasklist2024.ui.composables.TaskCard

@Composable
fun ListTasks(
    context: Context,
    onAddTaskClicked: () -> Unit,
    viewModel: ListTasksViewModel = hiltViewModel()
) {
    val tasks by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(topBar = {
        DefaultTopBar()
    }, floatingActionButton = {
        DefaultFAB(Icons.Filled.Add) {
            onAddTaskClicked()
        }
    }) { innerPadding ->
        when (tasks) {
            is ListTasksUIState.Success -> {
                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    items((tasks as ListTasksUIState.Success).tasks) { task ->
                        TaskCard(task)
                    }
                }
            }

            is ListTasksUIState.Error -> {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    DefaultError((tasks as ListTasksUIState.Error).throwable)
                }
            }

            is ListTasksUIState.Loading -> {
                Box(Modifier.fillMaxSize(), Alignment.Center) {
                    DefaultLoading()
                }
            }
        }

    }
}