package com.example.flupperapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flupperapp.data_classes.Main_tasks_data
import com.example.flupperapp.databinding.TaskItemBinding


class MainTasksAdapter(val mainTasksList: List<Main_tasks_data>) :
    RecyclerView.Adapter<MainTasksAdapter.ViewHolder>() {

    class ViewHolder(val binding: TaskItemBinding) : RecyclerView.ViewHolder(binding.root)

    //что тут вообще написано что такое context то
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = TaskItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = mainTasksList[position]
        with(holder.binding) {
            tvId.text = task.taskId
            tvText.text = task.text
            tvDescription.text = task.description

        }
    }

    override fun getItemCount(): Int {
        return mainTasksList.size
    }
}