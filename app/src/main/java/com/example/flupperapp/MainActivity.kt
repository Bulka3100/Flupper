package com.example.flupperapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.commit
import com.example.flupperapp.databinding.ActivityMainBinding
import com.example.flupperapp.fragments.FragmentTasks
import com.example.flupperapp.fragments.MainScreenFragment
import com.example.flupperapp.fragments.ProfileFragment
import com.example.flupperapp.utils.CurrencyManager

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    val binding get() = _binding ?: throw IllegalStateException("binding null")

    //!!! разобрать как тут написали
    private val database by lazy { (application as MyApplication).getDatabase() }
    private val currencyDAO by lazy { database.currencyDAO() }
    private val currencyManager by lazy { CurrencyManager(currencyDAO) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//разобрать
        binding.bottomNavigation.itemIconTintList = null



        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add(R.id.fragment, MainScreenFragment())
            }
        }
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            val handled = when (item.itemId) {
                R.id.menu_main -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(R.id.fragment, MainScreenFragment())
                    }
                    true
                }

                R.id.menu_tasks -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(R.id.fragment, FragmentTasks())
                    }
                    true
                }

                R.id.menu_profile -> {
                    supportFragmentManager.commit {
                        setReorderingAllowed(true)
                        replace(R.id.fragment, ProfileFragment())
                    }
                    true
                }

                else -> false
            }

            // Анимация иконок, если выбор обработан
            // разобрать подробнее
            if (handled) {
                for (i in 0 until binding.bottomNavigation.menu.size()) {
                    val menuItem = binding.bottomNavigation.menu.getItem(i)
                    val view = binding.bottomNavigation.findViewById<View>(menuItem.itemId)
                    if (menuItem.itemId == item.itemId) {
                        animateSelectedIcon(view)
                    } else {
                        resetIcon(view)
                    }
                }
            }
            handled // Возвращаем результат обработки
        }
    }

    private fun animateSelectedIcon(view: View) {
        view.animate()
            .scaleX(1.2f)
            .scaleY(1.2f)
            .setDuration(150)
            .start()

        // Добавим обводку: создаём программно, замени ic_launcher_background на подходящий для обводки ресурс
        view.background = ContextCompat.getDrawable(view.context, R.drawable.shape_selected_nav)

    }

    private fun resetIcon(view: View) {
        view.animate()
            .scaleX(1f)
            .scaleY(1f)
            .setDuration(150)
            .start()

        view.background = null
    }

}