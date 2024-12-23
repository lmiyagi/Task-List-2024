package com.leonardomiyagi.tasklist2024.data.repository

import com.leonardomiyagi.tasklist2024.data.dao.TaskDAO
import com.leonardomiyagi.tasklist2024.data.local.Task
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.times
import org.mockito.kotlin.verify

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
    fun `can add task with no errors`() = runTest {
        val generatedTaskId = 1L
        `when`(mockTaskDAO.insert(any())).thenReturn(generatedTaskId)
        `when`(mockTaskDAO.getById(any())).thenReturn(listOf(Task(generatedTaskId, "Test task")))

        val task = Task(title = "Test task")
        var addedTask: Task? = null
        addedTask = taskRepository.addTask(task)

        assertNotNull(addedTask)
        assertNotNull(addedTask.id)
        assertEquals(addedTask.id, generatedTaskId)
        assertEquals(task.title, addedTask.title)
        assertEquals(task.description, addedTask.description)
        assertEquals(task.isDone, addedTask.isDone)
        verify(mockTaskDAO, times(1)).insert(task)
    }

    @Test
    fun `can fail to add task and inform it`() = runTest {
        val generatedTaskId = 1L
        `when`(mockTaskDAO.getById(any())).thenReturn(listOf(Task(generatedTaskId, "Test task")))
        `when`(mockTaskDAO.insert(any())).thenReturn(-1L)

        val task = Task(title = "Test task")

        var exception: Exception? = null

        try {
            taskRepository.addTask(task)
        } catch (e: Exception) {
            exception = e
        }

        assertNotNull(exception)
        verify(mockTaskDAO, times(1)).insert(task)
    }

    @Test
    fun `can get a task by id`() = runTest {
        val taskId = 1L

        `when`(mockTaskDAO.getById(any())).thenReturn(listOf(Task(taskId, "Test task")))

        var retrievedTask: Task? = null

        retrievedTask = taskRepository.getTaskByID(taskId)

        assertNotNull(retrievedTask)
        assertNotNull(retrievedTask!!.id)
        assertEquals(retrievedTask.id, taskId)
        verify(mockTaskDAO, times(1)).getById(taskId)
    }

    @Test
    fun `can fail to get a task by id`() = runTest {
        val taskId = 1L

        `when`(mockTaskDAO.getById(any())).thenReturn(emptyList())

        var retrievedTask: Task? = null

        retrievedTask = taskRepository.getTaskByID(taskId)

        assertNull(retrievedTask)
        verify(mockTaskDAO, times(1)).getById(taskId)
    }

    @Test
    fun `can update a task with no errors`() = runTest {
        `when`(mockTaskDAO.update(any())).thenReturn(1)
        val task = Task(1L, "Task title")

        task.title = "Something"

        taskRepository.updateTask(task)

        verify(mockTaskDAO, times(1)).update(task)
    }

    @Test
    fun `can fail to update a task and inform`() = runTest {
        `when`(mockTaskDAO.update(any())).thenReturn(0)
        val task = Task(1L, "Task title")

        task.title = "Something"

        var exception: Exception? = null

        try {
            taskRepository.updateTask(task)
        } catch (e: Exception) {
            exception = e
        }

        assertNotNull(exception)
        verify(mockTaskDAO, times(1)).update(task)
    }

    @Test
    fun `can delete a task`() = runTest {
        val tasksIdsToDelete = listOf(1L)
        `when`(mockTaskDAO.deleteMultiple(any())).thenReturn(1)

        taskRepository.deleteTasksByID(tasksIdsToDelete)

        verify(mockTaskDAO, times(1)).deleteMultiple(tasksIdsToDelete)
    }

    @Test
    fun `can delete multiple tasks`() = runTest {
        val tasksIdsToDelete = listOf(1L, 2L, 3L)
        `when`(mockTaskDAO.deleteMultiple(any())).thenReturn(1)

        taskRepository.deleteTasksByID(tasksIdsToDelete)

        verify(mockTaskDAO, times(1)).deleteMultiple(tasksIdsToDelete)
    }

    @Test
    fun `can get a list of tasks`() = runTest {
        val task1 = Task(1L, "Task 1")
        val task2 = Task(1L, "Task 1")
        val task3 = Task(1L, "Task 1")

        val tasks = listOf(task1, task2, task3)
        `when`(mockTaskDAO.getAllTasks()).thenReturn(flowOf(tasks))

        assertEquals(taskRepository.getTasks().first(), tasks)
    }
}