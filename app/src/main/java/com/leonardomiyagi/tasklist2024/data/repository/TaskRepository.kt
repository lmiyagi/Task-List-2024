package com.leonardomiyagi.tasklist2024.data.repository

import com.leonardomiyagi.tasklist2024.core.exceptions.DatabaseErrorException
import com.leonardomiyagi.tasklist2024.data.dao.TaskDAO
import com.leonardomiyagi.tasklist2024.data.local.Task
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface TaskRepository {
    suspend fun addTask(task: Task): Task
    suspend fun updateTask(task: Task)
    suspend fun getTaskByID(id: Long): Task?
    fun getTasks(): Flow<List<Task>>
    suspend fun deleteTasksByID(ids: List<Long>)
}

class DefaultTaskRepository @Inject constructor(
    private val dao: TaskDAO
) : TaskRepository {
    override suspend fun addTask(task: Task): Task {
        val id = dao.insert(task)
        val addedTask = getTaskByID(id)

        if (id == -1L || addedTask == null) {
            throw DatabaseErrorException()
        }

        return addedTask
    }

    override suspend fun updateTask(task: Task) {
        if (dao.update(task) != 1) {
            throw DatabaseErrorException()
        }
    }

    override suspend fun getTaskByID(id: Long): Task? {
        return dao.getById(id).firstOrNull()
    }

    override fun getTasks(): Flow<List<Task>> {
        return dao.getAllTasks()
    }

    override suspend fun deleteTasksByID(ids: List<Long>) {
        dao.deleteMultiple(ids)
    }
}