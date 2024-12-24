package com.leonardomiyagi.tasklist2024.ui

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leonardomiyagi.tasklist2024.ui.composables.DefaultFAB
import com.leonardomiyagi.tasklist2024.ui.composables.DefaultTopBar
import com.leonardomiyagi.tasklist2024.ui.composables.TaskCard
import kotlinx.serialization.Serializable

@Serializable
object Main

@Composable
fun MainNavigation(context: Context) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Main) {
        composable<Main> {
            Scaffold(topBar = {
                DefaultTopBar()
            }, floatingActionButton = {
                DefaultFAB(Icons.Filled.Add) {
                    Toast.makeText(context, "Add", Toast.LENGTH_SHORT).show()
                }
            }) { innerPadding ->
                LazyColumn(modifier = Modifier.padding(innerPadding)) {
                    items(testTasks) { task ->
                        TaskCard(task)
                    }
                }
            }
        }
    }
}