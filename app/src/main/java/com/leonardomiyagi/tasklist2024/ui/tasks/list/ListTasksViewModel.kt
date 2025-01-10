package com.leonardomiyagi.tasklist2024.ui.tasks.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leonardomiyagi.tasklist2024.data.local.Task
import com.leonardomiyagi.tasklist2024.data.repository.TaskRepository
import com.leonardomiyagi.tasklist2024.ui.tasks.list.ListTasksUIState.Success
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
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

    private val _uiState = MutableStateFlow<ListTasksUIState>(ListTasksUIState.Loading)
    val uiState: StateFlow<ListTasksUIState> = _uiState

    init {
        viewModelScope.launch {
            try {
                taskRepository.getTasks().map { Success(it) }
                    .catch { _uiState.value = ListTasksUIState.Error(it) }
                    .stateIn(
                        viewModelScope,
                        SharingStarted.WhileSubscribed(5000),
                        ListTasksUIState.Loading
                    ).collect { tasks ->
                        _uiState.value = tasks
                    }
            } catch (e: Throwable) {
                _uiState.value = ListTasksUIState.Error(e)
            }
        }
    }
}

sealed interface ListTasksUIState {
    object Loading : ListTasksUIState
    data class Error(val throwable: Throwable) : ListTasksUIState
    data class Success(val tasks: List<Task>) : ListTasksUIState
}