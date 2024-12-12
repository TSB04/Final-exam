package com.example.finalexamapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TaskDao {
    @Query("SELECT * FROM Task")
    fun getAllTasks(): LiveData<List<Task>>

    @Query("SELECT * FROM Task WHERE id = :taskId")
    fun getTaskById(taskId: Int): LiveData<Task>

    @Insert
    fun insert(task: Task)

    @Update
    fun update(task: Task)

    @Query("DELETE FROM Task WHERE id = :taskId")
    fun delete(taskId: Int)
}
