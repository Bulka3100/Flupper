package com.example.flupperapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.flupperapp.MyApplication
import com.example.flupperapp.databinding.FragmentMainBinding
import com.example.flupperapp.utils.CurrencyManager


class MainScreenFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding ?: throw IllegalStateException("null binding")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return (binding.root)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val app = requireActivity().application as MyApplication
        val currencyManager = CurrencyManager(app.getDatabase().currencyDAO())
        currencyManager.setCurrencyView(binding.textViewCurrency)
        binding.testButton.setOnClickListener{
            currencyManager.awardCurrency(10)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

