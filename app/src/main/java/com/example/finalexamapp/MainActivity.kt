package com.example.finalexamapp

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalexamapp.adapters.MyAdapter
import com.example.finalexamapp.database.Task
import com.example.finalexamapp.database.TaskViewModel
import com.example.finalexamapp.databinding.ActivityMainBinding
import com.example.finalexamapp.databinding.DialogAddTaskBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = MyAdapter { task -> onTaskClicked(task) }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)
        taskViewModel.allTasks.observe(this) { tasks ->
            tasks?.let { adapter.setTasks(it) }
        }

        // Snackbar for welcome message
        Snackbar.make(binding.root, getString(R.string.welcome_message), Snackbar.LENGTH_LONG).show()

        // FloatingActionButton to add a task
        binding.fabAddTask.setOnClickListener {
            showAddTaskDialog()
        }
    }

//    private fun showAddTaskDialog() {
//        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)
//        val dialog = AlertDialog.Builder(this)
//            .setTitle(getString(R.string.add_task))
//            .setView(dialogBinding.root)
//            .setPositiveButton(getString(R.string.add_task), null)
//            .setNegativeButton(getString(R.string.cancel), null)
//            .create()
//
//        dialog.setOnShowListener {
//            val addButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
//            addButton.setOnClickListener {
//                val title = dialogBinding.editTextTaskTitle.text.toString().trim()
//                val description = dialogBinding.editTextTaskDescription.text.toString().trim()
//                val time = dialogBinding.editTextTaskTime.text.toString().trim()
//
//                if (title.isNotEmpty() && description.isNotEmpty() && time.isNotEmpty()) {
//                    val newTask = Task(title = title, description = description, time = time, isPassed = false)
//                    taskViewModel.insert(newTask)
//                    Toast.makeText(this, getString(R.string.task_added), Toast.LENGTH_SHORT).show()
//                    dialog.dismiss()
//                } else {
//                    Toast.makeText(this, getString(R.string.fields_empty), Toast.LENGTH_SHORT).show()
//                }
//            }
//        }
//
//        dialog.show()
//    }

    private fun showAddTaskDialog() {
        val dialogBinding = DialogAddTaskBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(this)
            .setTitle(getString(R.string.add_task))
            .setView(dialogBinding.root)
            .setPositiveButton(getString(R.string.add_task), null)
            .setNegativeButton(getString(R.string.cancel), null)
            .create()

        dialog.setOnShowListener {
            val addButton = dialog.getButton(AlertDialog.BUTTON_POSITIVE)
            addButton.setOnClickListener {
                val title = dialogBinding.editTextTaskTitle.text.toString().trim()
                val description = dialogBinding.editTextTaskDescription.text.toString().trim()
                val time = dialogBinding.editTextTaskTime.text.toString().trim()

                if (title.isNotEmpty() && description.isNotEmpty() && time.isNotEmpty()) {
                    val newTask = Task(title = title, description = description, time = time, isPassed = false)
                    taskViewModel.insert(newTask) // Appel sécurisé
                    Toast.makeText(this, getString(R.string.task_added), Toast.LENGTH_SHORT).show()
                    dialog.dismiss()
                } else {
                    Toast.makeText(this, getString(R.string.fields_empty), Toast.LENGTH_SHORT).show()
                }
            }
        }

        dialog.show()
    }


    private fun onTaskClicked(task: Task) {
        val intent = Intent(this, TaskDetailActivity::class.java).apply {
            putExtra("TASK_ID", task.id)
        }
        startActivity(intent)
    }
}
