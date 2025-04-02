package com.example.flupperapp.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flupperapp.data_classes.TaskData
import com.example.flupperapp.databinding.TaskItemBinding
// Добавляем импорт для ресурсов (иконок)
import com.example.flupperapp.R

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

            // Добавляем: Устанавливаем иконку в зависимости от статуса задачи
            ivIsReady.setImageResource(
                if (task.isComplited) R.drawable.ic_correct else R.drawable.ic_wrong
            )

            // Добавляем: Делаем иконку некликабельной, если задача уже выполнена
            ivIsReady.isClickable = !task.isComplited
            ivIsReady.isFocusable = !task.isComplited

            // Добавляем: Обрабатываем клик на иконку
            ivIsReady.setOnClickListener {
                if (!task.isComplited) {  // Проверяем, что задача ещё не выполнена
                    task.isComplited = true  // Отмечаем задачу как выполненную
                    ivIsReady.setImageResource(R.drawable.ic_correct)  // Меняем иконку
                    ivIsReady.isClickable = false  // Делаем иконку некликабельной
                    ivIsReady.isFocusable = false
                    onCompleteClick(task)  // Сообщаем фрагменту, что задача выполнена
                }
            }
        }
    }

    override fun getItemCount(): Int = taskSList.size

    fun updateTasks(newTasks: List<TaskData>) {
        taskSList.clear()
        taskSList.addAll(newTasks)
        notifyDataSetChanged()
    }
}