package com.example.flupperapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flupperapp.data_classes.TaskData
import com.example.flupperapp.databinding.TaskItemBinding
//почему с 0 так сложно адаптер писать самому надо импорты писать и тд


// вот как тут вообще можно понять? повторить надо наследование тут оно странное
// почему именно private val
// мы передаем не тот лист что в файле а рандоминый???
class TasksAdapter(private var taskSList: List<TaskData>):
    RecyclerView.Adapter<TasksAdapter.ViewHolders>() {

    class ViewHolders(val binding : TaskItemBinding) :RecyclerView.ViewHolder(binding.root)
    //что тут вообще написано что такое context то
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolders {
        val binding = TaskItemBinding.inflate(LayoutInflater.from(parent.context),
            parent,false)
        return ViewHolders(binding)
    }

    override fun onBindViewHolder(holder: ViewHolders, position: Int) {
        val task = taskSList[position]
        with(holder.binding){
           tvId.text = task.taskId
           tvText.text =task.text
           tvDescription.text =task.description
        }

    }

    override fun getItemCount(): Int = taskSList.size
}