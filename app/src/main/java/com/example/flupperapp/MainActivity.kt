package com.example.flupperapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.flupperapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    var _binding: ActivityMainBinding? = null
    val binding get() = _binding ?: throw IllegalStateException("binding null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment, Fragment_Main_Screen())

                binding.btnMain.setOnClickListener {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(R.id.fragment, Fragment_Main_Screen())


                        binding.btnTasks.setOnClickListener {
                            supportFragmentManager.commit {
                                setReorderingAllowed(true)
                                replace(R.id.fragment, FragmentTasks())
                            }
                        }
                    }
                }
            }
        }
    }
}
