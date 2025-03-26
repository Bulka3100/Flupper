package com.example.flupperapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.fragment.app.Fragment
import com.example.flupperapp.databinding.FragmentAddTaskBinding

class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("null binding")

    // Callback теперь передаёт только текст и описание
    private var onTaskAddedListener: ((String, String) -> Unit)? = null

    fun setOnTaskAddedListener(listener: (String, String) -> Unit) {
        onTaskAddedListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Делаем фрагмент полноэкранным
        activity?.window?.let { window ->
            WindowCompat.setDecorFitsSystemWindows(window, false)
            val controller = WindowInsetsControllerCompat(window, binding.root)
            controller.hide(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }

        binding.saveTaskButton.setOnClickListener {
            val text = binding.taskTextEditText.text.toString()
            val description = binding.taskDescriptionEditText.text.toString()
            if (text.isNotEmpty() && description.isNotEmpty()) {
                onTaskAddedListener?.invoke(text, description)
                parentFragmentManager.popBackStack()
            }
        }

        binding.cancelButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        // Возвращаем статус-бар и системную панель
        activity?.window?.let { window ->
            WindowCompat.setDecorFitsSystemWindows(window, true)
            val controller = WindowInsetsControllerCompat(window, binding.root)
            controller.show(WindowInsetsCompat.Type.statusBars() or WindowInsetsCompat.Type.navigationBars())
        }
        super.onDestroyView()
        _binding = null
    }
}