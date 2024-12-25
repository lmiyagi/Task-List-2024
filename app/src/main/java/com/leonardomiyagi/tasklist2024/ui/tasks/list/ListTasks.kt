package com.leonardomiyagi.tasklist2024.ui.tasks.list

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.leonardomiyagi.tasklist2024.ui.composables.DefaultFAB
import com.leonardomiyagi.tasklist2024.ui.composables.DefaultTopBar
import com.leonardomiyagi.tasklist2024.ui.composables.TaskCard

@Composable
fun ListTasks(context: Context, viewModel: ListTasksViewModel = hiltViewModel()) {
    val tasks by viewModel.uiState.collectAsStateWithLifecycle()
    Scaffold(topBar = {
        DefaultTopBar()
    }, floatingActionButton = {
        DefaultFAB(Icons.Filled.Add) {
            Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
            viewModel.addRandom()
        }
    }) { innerPadding ->
        LazyColumn(modifier = Modifier.padding(innerPadding)) {
            if (tasks is ListTasksUIState.Success) {
                items((tasks as ListTasksUIState.Success).tasks) { task ->
                    TaskCard(task)
                }
            }
        }
    }
}