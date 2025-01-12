package com.leonardomiyagi.tasklist2024.ui

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.leonardomiyagi.tasklist2024.ui.composables.CreateTask
import com.leonardomiyagi.tasklist2024.ui.tasks.list.ListTasks
import kotlinx.serialization.Serializable

@Serializable
object ListTasks

@Serializable
object CreateTask

@Composable
fun MainNavigation(context: Context) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = ListTasks) {
        composable<ListTasks> { ListTasks(context, { navController.navigate(CreateTask) }) }
        composable<CreateTask> {
            CreateTask(
                context,
                onBackClicked = { navController.navigateUp() })
        }
    }
}