package com.leonardomiyagi.tasklist2024.data.repository

import com.leonardomiyagi.tasklist2024.data.dao.TaskDAO
import com.leonardomiyagi.tasklist2024.data.local.Task
import javax.inject.Inject

interface TaskRepository {
    suspend fun addTask(task: Task): Task
    suspend fun updateTask(task: Task): Boolean
    suspend fun getTaskByID(id: Int): Task
    suspend fun getTasks(): List<Task>
    suspend fun deleteTaskByID(id: Int): Boolean
}

class DefaultTaskRepository @Inject constructor(
    private val dao: TaskDAO
) : TaskRepository {
    override suspend fun addTask(task: Task): Task {
        TODO("Not yet implemented")
    }

    override suspend fun updateTask(task: Task): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskByID(id: Int): Task {
        TODO("Not yet implemented")
    }

    override suspend fun getTasks(): List<Task> {
        TODO("Not yet implemented")
    }

    override suspend fun deleteTaskByID(id: Int): Boolean {
        TODO("Not yet implemented")
    }
}