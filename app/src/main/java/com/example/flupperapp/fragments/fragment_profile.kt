package com.example.flupperapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flupperapp.databinding.FragmentProfileBinding

//лучше разобрать ЖЦ фрагментов
// тут используем такую инициализацию binding так как иначе если мы раньше обратимся к нему чем инициализировали то все крашнется с nullpointer?
class ProfileFragment:Fragment(){
    private var _binding: FragmentProfileBinding? = null
    val binding get() = _binding ?: throw IllegalStateException("null binding")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }
}