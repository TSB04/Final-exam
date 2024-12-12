package com.example.finalexamapp

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.finalexamapp.database.Task
import com.example.finalexamapp.database.TaskViewModel
import com.example.finalexamapp.databinding.ActivityTaskDetailBinding

class TaskDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTaskDetailBinding
    private lateinit var taskViewModel: TaskViewModel
    private var taskId: Int = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTaskDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        taskId = intent.getIntExtra("TASK_ID", -1)
        if (taskId != -1) {
            taskViewModel.getTaskById(taskId).observe(this) { task ->
                task?.let {
                    binding.titleInput.setText(it.title)
                    binding.descriptionInput.setText(it.description)
                    binding.timeInput.setText(it.time)
                }
            }
        }

        binding.saveButton.setOnClickListener { saveTask() }
        binding.deleteButton.setOnClickListener { deleteTask() }
    }

    private fun saveTask() {
        val title = binding.titleInput.text.toString().trim()
        val description = binding.descriptionInput.text.toString().trim()
        val time = binding.timeInput.text.toString().trim()

        if (title.isEmpty() || description.isEmpty() || time.isEmpty()) {
            Toast.makeText(this, getString(R.string.fields_empty), Toast.LENGTH_SHORT).show()
            return
        }

        if (taskId == -1) {
            val newTask = Task(title = title, description = description, time = time, isPassed = false)
            taskViewModel.insert(newTask)
        } else {
            val updatedTask = Task(id = taskId, title = title, description = description, time = time, isPassed = false)
            taskViewModel.update(updatedTask)
        }

        Toast.makeText(this, getString(R.string.task_added), Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun deleteTask() {
        if (taskId != -1) {
            taskViewModel.delete(taskId)
            Toast.makeText(this, getString(R.string.task_deleted), Toast.LENGTH_SHORT).show()
        }
        finish()
    }
}
