package com.leonardomiyagi.tasklist2024.ui

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.leonardomiyagi.tasklist2024.core.base.BaseActivity
import com.leonardomiyagi.tasklist2024.data.local.Task
import com.leonardomiyagi.tasklist2024.ui.composables.DefaultFAB
import com.leonardomiyagi.tasklist2024.ui.composables.DefaultTopBar
import com.leonardomiyagi.tasklist2024.ui.composables.TaskCard
import com.leonardomiyagi.tasklist2024.ui.theme.TaskList2024Theme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    @Inject
    @Named("TEST_STRING")
    lateinit var test: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TaskList2024Theme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainNavigation(this)
                    Toast.makeText(this, test, Toast.LENGTH_SHORT).show()
                }
                /*Scaffold(
                    topBar = {
                        DefaultTopBar()
                    },
                    floatingActionButton = {
                        DefaultFAB(Icons.Filled.Add) {
                            Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show()
                        }
                    }
                ) { innerPadding ->
                    LazyColumn(modifier = Modifier.padding(innerPadding)) {
                        items(testTasks) { task ->
                            TaskCard(task)
                        }
                    }
                }*/
            }
        }
    }
}

val testTasks = listOf(
    Task(title = "Test 1", description = "ASPOikja SDKpoao koaSD kasd "),
    Task(title = "Test 2", description = "ASPOikja SDKpoao SDKpoao SDKpoao koaSD kasd "),
    Task(title = "Test 3"),
    Task(title = "Test 4", description = "ASPOkasd "),
    Task(
        title = "Test 5",
        description = "ASPOikja SDKpoao koaSD kasdSDKpoaoSDKpoaoSDKpoaoSDKpoao SDKpoaoSDKpoao SDKpoao "
    ),
    Task(title = "Test 1", description = "ASPOikja SDKpoao koaSD kasd "),
    Task(title = "Test 2", description = "ASPOikja SDKpoao SDKpoao SDKpoao koaSD kasd "),
    Task(title = "Test 3"),
    Task(title = "Test 4", description = "ASPOkasd "),
    Task(
        title = "Test 5",
        description = "ASPOikja SDKpoao koaSD kasdSDKpoaoSDKpoaoSDKpoaoSDKpoao SDKpoaoSDKpoao SDKpoao "
    ),
    Task(title = "Test 1", description = "ASPOikja SDKpoao koaSD kasd "),
    Task(title = "Test 2", description = "ASPOikja SDKpoao SDKpoao SDKpoao koaSD kasd "),
    Task(title = "Test 3"),
    Task(title = "Test 4", description = "ASPOkasd "),
    Task(
        title = "Test 5",
        description = "ASPOikja SDKpoao koaSD kasdSDKpoaoSDKpoaoSDKpoaoSDKpoao SDKpoaoSDKpoao SDKpoao "
    ),
    Task(title = "Test 1", description = "ASPOikja SDKpoao koaSD kasd "),
    Task(title = "Test 2", description = "ASPOikja SDKpoao SDKpoao SDKpoao koaSD kasd "),
    Task(title = "Test 3"),
    Task(title = "Test 4", description = "ASPOkasd "),
    Task(
        title = "Test 5",
        description = "ASPOikja SDKpoao koaSD kasdSDKpoaoSDKpoaoSDKpoaoSDKpoao SDKpoaoSDKpoao SDKpoao "
    ),
    Task(title = "Test 1", description = "ASPOikja SDKpoao koaSD kasd "),
    Task(title = "Test 2", description = "ASPOikja SDKpoao SDKpoao SDKpoao koaSD kasd "),
    Task(title = "Test 3"),
    Task(title = "Test 4", description = "ASPOkasd "),
    Task(
        title = "Test 5",
        description = "ASPOikja SDKpoao koaSD kasdSDKpoaoSDKpoaoSDKpoaoSDKpoao SDKpoaoSDKpoao SDKpoao "
    )
)

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    TaskList2024Theme {
        Scaffold(
            topBar = {
                DefaultTopBar()
            },
            floatingActionButton = {
                DefaultFAB(Icons.Filled.Add) {
                }
            }
        ) { innerPadding ->
            LazyColumn(modifier = Modifier.padding(innerPadding)) {
                items(testTasks) { task ->
                    TaskCard(task)
                }
            }
        }
    }
}