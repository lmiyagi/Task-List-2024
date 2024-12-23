package com.leonardomiyagi.tasklist2024.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.leonardomiyagi.tasklist2024.core.DB_FIELD_TASK_DESCRIPTION
import com.leonardomiyagi.tasklist2024.core.DB_FIELD_TASK_IS_DONE
import com.leonardomiyagi.tasklist2024.core.DB_FIELD_TASK_NAME
import com.leonardomiyagi.tasklist2024.core.DB_TABLE_TASKS

@Entity(tableName = DB_TABLE_TASKS)
data class Task(
    @PrimaryKey(autoGenerate = true) val id: Long? = null,
    @ColumnInfo(name = DB_FIELD_TASK_NAME) var title: String,
    @ColumnInfo(name = DB_FIELD_TASK_DESCRIPTION) var description: String? = null,
    @ColumnInfo(name = DB_FIELD_TASK_IS_DONE) var isDone: Boolean = false
)