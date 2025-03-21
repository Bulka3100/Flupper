package com.example.flupperapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flupperapp.databinding.FragmentTasksBinding
import com.example.flupperapp.adapters.TasksAdapter

class FragmentTasks:Fragment(){
    var _binding : FragmentTasksBinding? = null
    val binding get()=_binding ?: throw IllegalStateException ("null binding")
//    что принимает get в скобках
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Берём список категорий из STUB

        //почему именно так а нельзя просто список передать тоже самое же
        val tasks = STUB.showTasks()

        // Создаём адаптер
        val adapter = TasksAdapter(tasks)

        // Настраиваем RecyclerView как сетку
        binding.recyclerView.apply {
            this.adapter = adapter
//                что такое контекст тут зачем он нужен
            layoutManager = LinearLayoutManager(context)
    }
}

}