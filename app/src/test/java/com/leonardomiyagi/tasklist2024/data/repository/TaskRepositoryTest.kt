package com.leonardomiyagi.tasklist2024.data.repository

import com.leonardomiyagi.tasklist2024.data.dao.TaskDAO
import com.leonardomiyagi.tasklist2024.data.local.Task
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.doNothing
import org.mockito.kotlin.mock

class TaskRepositoryTest {

    private lateinit var taskRepository: TaskRepository
    private lateinit var mockTaskDAO: TaskDAO

    @Before
    fun setUp() {
        mockTaskDAO = mock<TaskDAO>()
        taskRepository = DefaultTaskRepository(mockTaskDAO)
    }

    @After
    fun tearDown() {
    }

    @Test
    fun `can add task`() {
        doNothing().`when`(mockTaskDAO).insert(any())

        val task = Task(title = "Test task")
        var addedTask: Task? = null
        runBlocking {
            addedTask = taskRepository.addTask(task)
        }

        assertNotNull(addedTask)
        assertNotNull(addedTask?.id)
        assertEquals(task.title, addedTask?.title)
        assertEquals(task.description, addedTask?.description)
        assertEquals(task.isDone, addedTask?.isDone)
    }

    @Test
    fun `can get a task by id`() {
        val taskId = 1

        `when`(mockTaskDAO.getById(any())).thenReturn(Task(1, "Test task"))

        var retrievedTask: Task? = null

        runBlocking {
            retrievedTask = taskRepository.getTaskByID(taskId)
        }

        assertNotNull(retrievedTask)
        assertEquals(retrievedTask!!.id, taskId)
    }

    @Test
    fun `can update a task and confirm`() {
        doNothing().`when`(mockTaskDAO).insert(any())

        val task = createTaskWithoutID()
        var addedTask: Task? = null

        runBlocking {
            addedTask = taskRepository.addTask(task)
        }

        assertNotNull(addedTask)
        assertNotNull(addedTask?.id)

        var isUpdated = false

        `when`(mockTaskDAO.update(any())).thenReturn(1)

        runBlocking {
            isUpdated = taskRepository.updateTask(addedTask!!)
        }

        assertTrue(isUpdated)
    }

    @Test
    fun `can fail to update a task and inform`() {
        doNothing().`when`(mockTaskDAO).insert(any())

        val task = createTaskWithoutID()
        var addedTask: Task? = null

        runBlocking {
            addedTask = taskRepository.addTask(task)
        }

        assertNotNull(addedTask)
        assertNotNull(addedTask?.id)

        var isUpdated = false

        `when`(mockTaskDAO.update(any())).thenReturn(0)

        runBlocking {
            isUpdated = taskRepository.updateTask(addedTask!!)
        }

        assertFalse(isUpdated)
    }

    @Test
    fun `can delete a task and confirm`() {
        `when`(mockTaskDAO.delete(any())).thenReturn(1)

        val task = Task(1, "Test task")

        var isDeleted = false

        runBlocking {
            isDeleted = taskRepository.deleteTaskByID(task.id!!)
        }

        assertTrue(isDeleted)
    }

    @Test
    fun `can fail to delete a task and inform`() {
        `when`(mockTaskDAO.delete(any())).thenReturn(0)

        val task = Task(1, "Test task")

        var isDeleted = false

        runBlocking {
            isDeleted = taskRepository.deleteTaskByID(task.id!!)
        }

        assertFalse(isDeleted)
    }

    private fun createTaskWithoutID(): Task {
        return Task(title = "Test task")
    }
}