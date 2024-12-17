package com.leonardomiyagi.tasklist2024.data.model

data class Task(
    val id: Int,
    val title: String,
    val description: String,
    val isDone: Boolean
)