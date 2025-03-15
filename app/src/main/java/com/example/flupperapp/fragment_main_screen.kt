package com.example.flupperapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flupperapp.adapters.MainTasksAdapter
import com.example.flupperapp.adapters.TasksAdapter
import com.example.flupperapp.databinding.FragmentMainBinding


class Fragment_Main_Screen : Fragment() {
    var _binding: FragmentMainBinding? = null
    val binding get() = _binding ?: throw IllegalStateException("null binding")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

