package com.leonardomiyagi.tasklist2024.ui.tasks.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonardomiyagi.tasklist2024.data.local.Task
import com.leonardomiyagi.tasklist2024.data.repository.TaskRepository
import com.leonardomiyagi.tasklist2024.ui.tasks.list.ListTasksUIState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ListTasksViewModel @Inject constructor(
    private val taskRepository: TaskRepository
) : ViewModel() {

    val uiState: StateFlow<ListTasksUIState> = taskRepository.getTasks()
        .map<List<Task>, ListTasksUIState>(::Success)
        .catch { it.printStackTrace() }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), ListTasksUIState.Loading)

    // TODO remove this
    fun addRandom() {
        val randomNumber = Random.nextInt(100)
        viewModelScope.launch { taskRepository.addTask(Task(title = "Test task $randomNumber")) }
    }
}

sealed interface ListTasksUIState {
    object Loading : ListTasksUIState
    data class Error(val throwable: Throwable) : ListTasksUIState
    data class Success(val tasks: List<Task>) : ListTasksUIState
}