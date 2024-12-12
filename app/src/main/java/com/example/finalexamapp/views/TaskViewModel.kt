package com.example.finalexamapp.database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val taskDao: TaskDao = AppDatabase.getDatabase(application).taskDao()
    val allTasks: LiveData<List<Task>> = taskDao.getAllTasks()

    fun insert(task: Task) {
        viewModelScope.launch(Dispatchers.IO) { // Opération sur un thread en arrière-plan
            taskDao.insert(task)
        }
    }

    fun getTaskById(taskId: Int): LiveData<Task> {
        return taskDao.getTaskById(taskId)
    }

    fun update(task: Task) {
        viewModelScope.launch(Dispatchers.IO) { // Opération sur un thread en arrière-plan
            taskDao.update(task)
        }
    }

    fun delete(taskId: Int) {
        viewModelScope.launch(Dispatchers.IO) { // Opération sur un thread en arrière-plan
            taskDao.delete(taskId)
        }
    }
}
