package com.example.flupperapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flupperapp.data_classes.TaskData
import com.example.flupperapp.databinding.TaskItemBinding

class TasksAdapter(
    private val taskSList: MutableList<TaskData>,
    private val onCompleteClick: (TaskData) -> Unit = {}
) : RecyclerView.Adapter<TasksAdapter.ViewHolders>() {

    class ViewHolders(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolders {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolders(binding)
    }

    override fun onBindViewHolder(holder: ViewHolders, position: Int) {
        val task = taskSList[position]
        with(holder.binding) {
            tvIsReady.text = task.taskId
            tvText.text = task.text
            tvDescription.text = task.description
        }
    }

    override fun getItemCount(): Int = taskSList.size

    fun updateTasks(newTasks: List<TaskData>) {
        taskSList.clear()
        taskSList.addAll(newTasks)
        notifyDataSetChanged()
    }
}