package com.leonardomiyagi.tasklist2024.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.leonardomiyagi.tasklist2024.core.DB_FIELD_TASK_ID
import com.leonardomiyagi.tasklist2024.core.DB_TABLE_TASKS
import com.leonardomiyagi.tasklist2024.data.local.Task
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDAO {

    @Insert
    suspend fun insert(task: Task)

    @Delete
    suspend fun delete(task: Task): Int

    @Update
    suspend fun update(task: Task): Int

    @Query("SELECT * FROM $DB_TABLE_TASKS WHERE $DB_FIELD_TASK_ID IS :id")
    suspend fun getById(id: Int): Task

    @Query("SELECT * FROM $DB_TABLE_TASKS")
    fun getAllTasks(): Flow<List<Task>>
}