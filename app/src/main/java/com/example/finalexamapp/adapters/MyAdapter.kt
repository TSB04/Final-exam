package com.example.finalexamapp.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.finalexamapp.R
import com.example.finalexamapp.database.Task

class MyAdapter(private val onTaskClick: (Task) -> Unit) : RecyclerView.Adapter<MyAdapter.TaskViewHolder>() {
    private var tasks = emptyList<Task>()

    // ViewHolder to hold item views
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val taskTitle: TextView = itemView.findViewById(R.id.taskTitle)
        private val taskDescription: TextView = itemView.findViewById(R.id.taskDescription)

        fun bind(task: Task) {
            taskTitle.text = task.title
            taskDescription.text = task.description
            itemView.setOnClickListener { onTaskClick(task) }
        }
    }

    // Inflate the item layout and create ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return TaskViewHolder(view)
    }

    // Bind the data to the ViewHolder
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    // Return the size of the data list
    override fun getItemCount(): Int = tasks.size

    // Update the data and refresh the RecyclerView
    fun setTasks(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}
