package com.leonardomiyagi.tasklist2024.ui.composables

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.compose.rememberNavController
import com.leonardomiyagi.tasklist2024.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTask(context: Context, onBackClicked: () -> Unit) {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBar(title = { Text(stringResource(R.string.create_task_title)) },
                navigationIcon = {
                    IconButton(onClick = { onBackClicked() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.global_back)
                        )
                    }
                })
        }
    ) { innerPadding ->
        Row(modifier = Modifier.padding(innerPadding)) {
            Text(modifier = Modifier.fillMaxWidth(), text = "Create task")
            Button({}) {
                Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
            }
        }
    }
}