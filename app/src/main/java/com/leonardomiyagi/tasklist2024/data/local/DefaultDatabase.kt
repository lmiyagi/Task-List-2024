package com.leonardomiyagi.tasklist2024.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.leonardomiyagi.tasklist2024.data.dao.TaskDAO

@Database(
    entities = [Task::class],
    version = 1,
    exportSchema = false
)
abstract class DefaultDatabase : RoomDatabase() {

    abstract fun taskDAO(): TaskDAO
}