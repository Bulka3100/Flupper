package com.example.flupperapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flupperapp.R
import com.example.flupperapp.STUB
import com.example.flupperapp.adapters.TasksAdapter
import com.example.flupperapp.data_classes.TaskData
import com.example.flupperapp.databinding.FragmentTasksBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class FragmentTasks : Fragment() {
    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("null binding")

    //!!! почему тут нужен lataInшt а если его нет то ломается
    private lateinit var dailyTasksAdapter: TasksAdapter
    private lateinit var mainTasksAdapter: TasksAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Настройка адаптеров для RecyclerView
        // Преобразуем List в MutableList для dailyTasksAdapter
        val dailyTasks = STUB.showTasks().toMutableList()
        dailyTasksAdapter = TasksAdapter(dailyTasks) { task ->
            task.isComplited = true
            dailyTasksAdapter.updateTasks(STUB.showTasks().toMutableList())
        }
        // Аналогично для mainTasksAdapter
        val mainTasks = STUB.showGlobalTasks().toMutableList()
        mainTasksAdapter = TasksAdapter(mainTasks) { task ->
            task.isComplited = true
            mainTasksAdapter.updateTasks(STUB.showGlobalTasks().toMutableList())
        }

        binding.recyclerViewTasks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dailyTasksAdapter
        }

        binding.recyclerViewMainTasks.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mainTasksAdapter
        }

        // Открываем AddTaskFragment по кнопке "Добавить задачу"
        binding.addTaskButton.setOnClickListener {
            requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.GONE

            val addTaskFragment = AddTaskFragment()
            addTaskFragment.setOnTaskAddedListener { text, description ->
                val newTaskId = (STUB.showTasks().size + STUB.showGlobalTasks().size + 1).toString()
                val newTask = TaskData(text, newTaskId, description, false, 5)
                STUB.addDailyTask(newTask)
                dailyTasksAdapter.updateTasks(STUB.showTasks().toMutableList())
            }
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragment, addTaskFragment)
                .addToBackStack(null)
                .commit()
        }
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottom_navigation)?.visibility = View.VISIBLE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}